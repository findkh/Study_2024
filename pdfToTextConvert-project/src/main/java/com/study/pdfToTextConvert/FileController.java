package com.study.pdfToTextConvert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

    private static final String UPLOAD_DIR = "src/main/resources/static/originFile/";

    @PostMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            try {
                // 업로드 디렉토리가 존재하지 않으면 생성
                Path uploadPath = Path.of(UPLOAD_DIR);
                if (Files.notExists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // 파일을 업로드 디렉토리로 복사
                Path targetPath = uploadPath.resolve(file.getOriginalFilename());
                Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

                return "파일 업로드 성공: " + file.getOriginalFilename();
            } catch (IOException e) {
                return "파일 업로드 실패: " + e.getMessage();
            }
        } else {
            return "파일이 비어있습니다.";
        }
    }
    
    
    //PDF 파일을 POST로 직접 다운로드 하려고 하면 다운로드가 안됨
    //POST를 사용하려면 POST로 받아서 GET 컨트롤러를 만들어서 다시 요청하는식으로 구현해야함.
    
    @GetMapping("/download")
    public ResponseEntity<FileSystemResource> downloadPDFFile(@RequestParam String fileName) {
        try {
            // 파일 경로 조합
            Path filePath = Path.of(UPLOAD_DIR, fileName);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(new FileSystemResource(filePath.toFile()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
//    @PostMapping("/download")
//    public ResponseEntity<FileSystemResource> downloadPDFFile(@RequestBody Map<String, String> param) {
//        try {
//            String fileName = param.get("fileName");
//
//            // 파일 경로 조합
//            Path filePath = Path.of(UPLOAD_DIR, fileName);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
//            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);
//
//            return ResponseEntity
//                    .ok()
//                    .headers(headers)
//                    .body(new FileSystemResource(filePath.toFile()));
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
    
//    @GetMapping("/download")
//    public ResponseEntity<FileSystemResource> downloadPDFFile() {
//        try {
//            Path filePath = Path.of(UPLOAD_DIR, "dd98f603-4a38-4af3-8278-2e5186b9a297_pdf_test.pdf");
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=dd98f603-4a38-4af3-8278-2e5186b9a297_pdf_test.pdf");
//            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);
//
//            return ResponseEntity
//                    .ok()
//                    .headers(headers)
//                    .body(new FileSystemResource(filePath.toFile()));
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
    

    @PostMapping("/pdfToText")
    @ResponseBody
    public ResponseEntity<Map<String, String>> pdfToText(@RequestPart("file") MultipartFile file) {
        Map<String, String> response = new HashMap<>();

        if (file.isEmpty()) {
            response.put("status", "fail");
            response.put("message", "Please select a file!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            // PDF 파일을 PDDocument 객체로 로드
            PDDocument document = PDDocument.load(file.getInputStream());

            // PDF 문서의 텍스트 추출을 위한 PDFTextStripper 객체 생성
            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            // PDF 문서의 텍스트 추출
            String text = pdfTextStripper.getText(document);

            // PDDocument 리소스 해제
            document.close();

            // 추출된 텍스트를 콘솔에 출력
            System.out.println(text);

            response.put("status", "success");
            response.put("data", text);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            e.printStackTrace();
            response.put("status", "fail");
            response.put("message", "Failed to convert PDF to text!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}