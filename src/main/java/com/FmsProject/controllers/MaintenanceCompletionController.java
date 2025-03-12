package com.FmsProject.controllers;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.FmsProject.models.MaintenanceCompletionModel;
import com.FmsProject.models.MaintenanceCostReportModel;
import com.FmsProject.services.MaintenanceCompletionService;

@RestController
@CrossOrigin
@RequestMapping("/maintenance")
public class MaintenanceCompletionController {
	  @Value("${upload.directory}")
    private String uploadDir; // The directory where files will be saved
	@Value("${download.directory}")
    private String downloadDirs;

	@Autowired
	private MaintenanceCompletionService _service;

	@GetMapping("/getAll")
	public List<MaintenanceCompletionModel> getMaintenanceCompletion() {
		return _service.getMaintenanceCompletion();
	}

	@GetMapping("/costCount")
	public Float maintenanceConstCount() {
		return _service.maintenanceConstCount();
	}

	@PostMapping("/complete")
	public ResponseEntity<MaintenanceCompletionModel> completeMaintenance(
			@RequestBody MaintenanceCompletionModel comp) {
		MaintenanceCompletionModel d = _service.completeMaintenance(comp);
		return new ResponseEntity(d, HttpStatus.OK);
	}

	// maintenance cost by monthly
	@PostMapping("costreportbymonthrange")
	public ResponseEntity<List<MaintenanceCompletionModel>> costReport(@RequestBody MaintenanceCostReportModel report) {
		List<MaintenanceCompletionModel> response = _service.costReport(report);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// @GetMapping("/costreportbyplateno/{req}")
	// public List<MaintenanceCompletionModel>
	// byplateNocostReport(@PathVariable("req") String req) {
	// System.out.println(req);
	// return _service.byplateNocostReport(req);
	// }

	@PostMapping("costreportbyplateno")
	public Float byplateNocostReport(@RequestBody MaintenanceCostReportModel report) {
		// System.out.println(report.getPlateNo());
		Float response = _service.byplateNocostReport(report.getPlateNo());
		return new Float(response);
	}

	// total maintenance cost by plate number
	@PostMapping("totalplatecost")
	public Float totalPlateNumberCost(@RequestBody MaintenanceCostReportModel report) {
		// System.out.println(report.getPlateNo());
		return _service.totalplatecost(report.getPlateNo());
	}

	// maintenance cost by yearly
	@PostMapping("costreportbyyearrange")
	public ResponseEntity<List<MaintenanceCompletionModel>> yearlyMaintenanceCostReport(
			@RequestBody MaintenanceCostReportModel report) {
		List<MaintenanceCompletionModel> response = _service.yearlyMaintenanceCostReport(report);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// total maintenance cost by month
	@PostMapping("totalmonthlycost")
	public double totalMonthlyCost(@RequestBody MaintenanceCostReportModel report) {
		return _service.totalMonthlyCost(report);
	}

	// total maintenance cost by yearly
	@PostMapping("totalyearlycost")
	public double totalYearlyCost(@RequestBody MaintenanceCostReportModel report) {
		return _service.totalYearlyCost(report);
	}

	@PutMapping("/updatecompletion")
	public void updateCompletion(@RequestBody MaintenanceCompletionModel ment) {
		System.out.println("Incoming Updates:"+ment);
		_service.updateCompletion(ment);
	}
	//For Checkups
	 @PutMapping(value = "/updatescompletion", consumes = "multipart/form-data")
    public void updateCompletion(
            @RequestParam("file") MultipartFile file,
            @RequestParam("id") Integer id,
            @RequestParam("dateFromTechnical") String dateFromTechnical,
            @RequestParam("maintenanceCost") Float maintenanceCost,
            @RequestParam("dateTaken") String dateTaken,
            @RequestParam("dateFromGarage") String dateFromGarage,
            @RequestParam("status") String status) {
        
        MaintenanceCompletionModel ment = new MaintenanceCompletionModel();
        ment.setId(id);
        ment.setDateFromTechnical(dateFromTechnical);
        ment.setMaintenanceCost(maintenanceCost);
        ment.setDateTaken(dateTaken);
        ment.setDateFromGarage(dateFromGarage);
        ment.setStatus(status);
        
        System.out.println("Incoming Updates: " + ment);
		
		 // Log file details
		 if (file != null && !file.isEmpty()) {

           String projectRoot = System.getProperty("user.dir"); 
            Path uploadPath = Paths.get(projectRoot, uploadDir,"files");
 // Ensure the upload directory exists
 File uploadDirFile = new File(uploadDir);
 if (!uploadDirFile.exists()) {
	 uploadDirFile.mkdirs(); // Create the directory if it doesn't exist
 }



			try{

            // Generate a unique file name
            String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(uniqueFileName);
            File destinationFile = filePath.toFile();






            // Save the file
            file.transferTo(destinationFile);

			//String filePath = uploadDir + File.separator + file.getOriginalFilename();
			//File destinationFile = new File(filePath);
			   // Save the file
			   file.transferTo(destinationFile);
			ment.setFilePath(filePath.toString());



			_service.updateCompletion(ment);
            System.out.println("Uploaded File Details:");
            System.out.println("Original Filename: " + file.getOriginalFilename());
            System.out.println("File Size: " + file.getSize() + " bytes");
            System.out.println("Content Type: " + file.getContentType());
			System.out.println("File Path: " + filePath.toString());
            // You can log more details if needed
			}catch(Exception e){
				e.printStackTrace();

			}
        } 
	
		else {
            System.out.println("No file uploaded.");
			_service.updateCompletion(ment);
        }

        // _service.updateCompletion(ment);

    }
	


@GetMapping("/download/{fileName}")
public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
    try {
		System.out.println("Trying to download from first: " + fileName+""+downloadDirs);
		
		String projectRoot = System.getProperty("user.dir"); 
		//Path uploadPath = Paths.get(projectRoot, uploadDir,"files");
        Path filePath = Paths.get(projectRoot, uploadDir,"files",fileName).normalize();
		System.out.println("Trying to download from second: " + filePath);
        Resource resource = new UrlResource(filePath.toUri());
		System.out.println("Trying to download from third: " + resource.toString());
        if (resource.exists() || resource.isReadable()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    } catch (Exception e) {
        return ResponseEntity.internalServerError().build();
    }
}

}
