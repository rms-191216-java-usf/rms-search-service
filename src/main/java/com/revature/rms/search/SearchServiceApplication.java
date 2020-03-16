package com.revature.rms.search;

import com.netflix.discovery.converters.Auto;
import com.revature.rms.search.entites.batch.Batch;
import com.revature.rms.search.entites.batch.Curriculum;
import com.revature.rms.search.entites.batch.ResourceMetadata;
import com.revature.rms.search.entites.workorder.Category;
import com.revature.rms.search.entites.workorder.WorkOrder;
import com.revature.rms.search.repositories.BatchRepository;
import com.revature.rms.search.repositories.WorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EnableFeignClients
@EnableEurekaClient
@EnableSwagger2
@SpringBootApplication
public class SearchServiceApplication {

  private static BatchRepository batchRepo;
  private static WorkOrderRepository workOrderRepo;

  @Autowired
  public SearchServiceApplication(
      BatchRepository batchRepository, WorkOrderRepository workOrderRepository) {
    batchRepo = batchRepository;
    workOrderRepo = workOrderRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(SearchServiceApplication.class, args);
    ResourceMetadata resource = new ResourceMetadata(1, "12/16/20", 1, null, 1);
    List<Integer> associates = new ArrayList<>();
    associates.add(25);
    associates.add(26);
    associates.add(27);
    WorkOrder w =
        new WorkOrder(
            "1",
            "01/02/20",
            "01/05/20",
            Category.LIGHTING,
            "Needs a new light bulb",
            "buhlakay@gmail.com",
            1,
            2);
    WorkOrder w1 =
            new WorkOrder(
                    "2",
                    "01/02/20",
                    "01/05/20",
                    Category.LIGHTING,
                    "Needs a new light bulb",
                    "buhlakay@gmail.com",
                    1,
                    2);
    WorkOrder w2 =
            new WorkOrder(
                    "3",
                    "01/02/20",
                    "01/05/20",
                    Category.LIGHTING,
                    "Needs a new light bulb",
                    "buhlakay@gmail.com",
                    1,
                    2);
    Batch b =
        new Batch(
            "1",
            "191216-java-usf",
            LocalDate.of(2019, 12, 16),
            LocalDate.of(2020, 3, 20),
            1,
            0,
            associates,
            Curriculum.JAVA_MSA,
            resource);
    Batch b1 =
            new Batch(
                    "2",
                    "200105-java-usf",
                    LocalDate.of(2020, 1, 5),
                    LocalDate.of(2020, 4, 7),
                    2,
                    0,
                    associates,
                    Curriculum.JAVA_MSA,
                    resource);
    Batch b2 =
            new Batch(
                    "3",
                    "191216-java-usf",
                    LocalDate.of(2019, 12, 16),
                    LocalDate.of(2020, 3, 20),
                    3,
                    0,
                    associates,
                    Curriculum.JAVA_MSA,
                    resource);
    workOrderRepo.save(w);
    workOrderRepo.save(w1);
    workOrderRepo.save(w2);
    batchRepo.save(b);
    batchRepo.save(b1);
    batchRepo.save(b2);
  }

  @Bean
  public Docket swagger() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage(this.getClass().getPackage().getName()))
        .paths(PathSelectors.any())
        .build()
        .useDefaultResponseMessages(false);
  }
}
