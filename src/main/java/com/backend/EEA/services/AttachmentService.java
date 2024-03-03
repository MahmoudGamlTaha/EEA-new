package com.backend.EEA.services;

import com.backend.EEA.business.dao.repositories.masterdat.AttachmentRepository;
import com.backend.EEA.mapper.masterdata.AttachmentMapper;
import com.backend.EEA.model.dto.masterdata.AttachmentDto;
import com.backend.EEA.model.dto.search.AttachmentSearchForm;
import com.backend.EEA.model.entity.masterdata.Attachment;
import com.backend.EEA.model.enums.ResponseMessageEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttachmentService extends BaseService<Attachment, AttachmentDto, AttachmentSearchForm> {
     @Value("${ftp.sever.storage.path}")
    private String path = "./files/";
    AttachmentRepository attachmentRepository;
    AttachmentMapper attachmentMapper;

    private final Path rootLocation;

    public AttachmentService(AttachmentRepository attachmentRepository, AttachmentMapper attachmentMapper){
      super(attachmentRepository);
      this.attachmentRepository = attachmentRepository;
      this.attachmentMapper = attachmentMapper;
      rootLocation = Paths.get(path);
    }
    @Override
    protected Specification<Attachment> buildSpecification(AttachmentSearchForm searchParams) {
        return null;
    }

    @Override
    protected List<AttachmentDto> mapDataListToDtoList(List<Attachment> list) {
        return list.stream().map(attachmentMapper::toAttachmentDto).collect(Collectors.toList());
    }

    @Override
    protected AttachmentDto prepareEntityToDto(Attachment object) {
        return this.attachmentMapper.toAttachmentDto(object);
    }

    @Override
    protected Attachment prepareDtoToEntity(AttachmentDto object) {
        return this.attachmentMapper.toAttachment(object);
    }

    @Override
    protected void setIdBeforeUpdate(long id, Attachment object) {
        object.setId(id);
    }

    @Override
    public AttachmentSearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery, AttachmentSearchForm.class);
    }
    public List<AttachmentDto> upload(List<MultipartFile> files, int type){
        List<AttachmentDto> attachmentDtos = new LinkedList<>();
        List<Attachment> attachments = new LinkedList<>();
        switch (type){
            case 1 :  // for companies
            {
                files.stream().forEach( file -> {
                  URI uri = this.saveFile(file);
                  Attachment attachment = new Attachment();
                  attachment.setUrl(uri.toString());
                  attachment.setName(file.getOriginalFilename());
                  attachment.setTemp(true);
                  attachment.setEntityId(getEntityId());
                  attachments.add(attachment);
                });
                attachmentDtos = this.mapDataListToDtoList(attachmentRepository.saveAll(attachments));
            }
        }
        return attachmentDtos;
    }
   // https://medium.com/@patelsajal2/how-to-create-a-spring-boot-rest-api-for-multipart-file-uploads-a-comprehensive-guide-b4d95ce3022b
    private URI saveFile(MultipartFile file){
        Path destination = this.rootLocation.resolve(Paths.get("C:\\xampp\\htdocs\\eea\\documents\\"+getLoggedInUserId()+"_"+file.getOriginalFilename()).normalize().toAbsolutePath());
      System.out.println(destination);
       try(InputStream stream = file.getInputStream()){

           Files.copy(stream, destination, StandardCopyOption.REPLACE_EXISTING);
       }catch (IOException e) {
           System.out.println("error storage");
       }
       try {
         /*  return new URI(ServletUriComponentsBuilder.fromCurrentContextPath()
                   .path(this.rootLocation.toString())
                   .path(destination.toString())
                   .toUriString());*/
          return new URI(ServletUriComponentsBuilder.fromCurrentContextPath().port(8050)
                   .path("/docs/")
                   .path(getLoggedInUserId()+"_"+file.getOriginalFilename()).toUriString());
       }catch (URISyntaxException ex){
          ex.printStackTrace();
       }
       return destination.normalize().toAbsolutePath().toUri();
    }
}
