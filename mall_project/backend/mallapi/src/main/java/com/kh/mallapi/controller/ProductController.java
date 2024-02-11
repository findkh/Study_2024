package com.kh.mallapi.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.mallapi.dto.PageRequestDTO;
import com.kh.mallapi.dto.PageResponseDTO;
import com.kh.mallapi.dto.ProductDTO;
import com.kh.mallapi.service.ProductService;
import com.kh.mallapi.util.CustomFileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable

;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/products")
public class ProductController {
    private final CustomFileUtil fileUtil;
    private final ProductService productService;

    @PostMapping("/")
    public Map<String, Long> register(ProductDTO productDTO) {
        log.info("register: {}", productDTO);

        List<MultipartFile> files = productDTO.getFiles();
        List<String> uploadFileNames = fileUtil.saveFiles(files);
        productDTO.setUploadFileNames(uploadFileNames);
        log.info(uploadFileNames);

        Long pno = productService.register(productDTO);

        return Map.of("result", pno);
    }

    // @PostMapping("/")
    // public Map<String, Long> register(ProductDTO productDTO) {
    // log.info("register: {}", productDTO);

    // List<MultipartFile> files = productDTO.getFiles();
    // List<String> uploadFileNames = fileUtil.saveFiles(files);
    // productDTO.setUploadFileNames(uploadFileNames);
    // log.info(uploadFileNames);

    // Long pno = productService.register(productDTO);

    // Map<String, Long> result = null;

    // try {
    // System.out.println("sleep.....");
    // Thread.sleep(6000);
    // result = Map.of("result", pno);
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }

    // return result;
    // }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGET(@PathVariable String fileName) {
        System.out.println("============================");
        System.out.println(fileName);
        return fileUtil.getFile(fileName);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')") // 'ROLE_USER',
    @GetMapping("/list")
    public PageResponseDTO<ProductDTO> list(PageRequestDTO pageRequestDTO) {
        log.info("list: {}", pageRequestDTO);
        return productService.getList(pageRequestDTO);
    }

    @GetMapping("/{pno}")
    public ProductDTO read(@PathVariable(name = "pno") Long pno) {
        return productService.get(pno);
    }

    @PutMapping("/{pno}")
    public Map<String, String> modify(@PathVariable(name = "pno") Long pno, ProductDTO productDTO) {
        productDTO.setPno(pno);
        ProductDTO oldProductDTO = productService.get(pno);
        List<String> oldFileNames = oldProductDTO.getUploadFileNames();
        List<MultipartFile> files = productDTO.getFiles();
        List<String> currentUploadFileNames = fileUtil.saveFiles(files);
        List<String> uploaddedFileNames = productDTO.getUploadFileNames();

        if (currentUploadFileNames != null && currentUploadFileNames.size() > 0) {
            uploaddedFileNames.addAll(currentUploadFileNames);
        }

        productService.modify(productDTO);

        if (oldFileNames != null && oldFileNames.size() > 0) {
            List<String> removeFiles = oldFileNames.stream()
                    .filter(fileName -> uploaddedFileNames.indexOf(fileName) == -1).collect(Collectors.toList());
            fileUtil.deleteFiles(removeFiles);
        }
        return Map.of("RESULT", "SUCCESS");
    }

    @DeleteMapping("/{pno}")
    public Map<String, String> remove(@PathVariable("pno") Long pno) {
        List<String> oldFileNames = productService.get(pno).getUploadFileNames();
        System.out.println("이전 파일=====ㄴ");
        System.out.println(oldFileNames);
        productService.remove(pno);
        fileUtil.deleteFiles(oldFileNames);
        return Map.of("RESULT", "SUCCESS");
    }
}
