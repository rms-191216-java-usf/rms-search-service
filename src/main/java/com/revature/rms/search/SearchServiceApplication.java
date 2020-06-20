package com.revature.rms.search;

import com.revature.rms.search.entites.batch.Batch;
import com.revature.rms.search.entites.batch.Curriculum;
import com.revature.rms.search.entites.common.ResourceMetadata;
import com.revature.rms.search.entites.workorder.Category;
import com.revature.rms.search.entites.workorder.WorkOrder;
import com.revature.rms.search.repositories.BatchRepository;
import com.revature.rms.search.repositories.WorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
//@EnableSwagger2
@SpringBootApplication
public class SearchServiceApplication implements CommandLineRunner {

  /**
   * These can get be deleted once the services are implemented.
   * */
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
  }

 /* @Bean
  public Docket swagger() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage(this.getClass().getPackage().getName()))
        .paths(PathSelectors.any())
        .build()
        .useDefaultResponseMessages(false);
  }*/
  /**
   * This can be removed once the batch and work order services are implemented. Make sure to delete
   * the Command Line Runner implementation in the class path so that you don't get errors thrown.
   * */
  @Override
  public void run(String... args) throws Exception {
    ResourceMetadata resource = new ResourceMetadata(1,1, "12/16/20", 1, "12/16/20", 1, true);
    List<Integer> associates = new ArrayList<>();
    associates.add(18);
    associates.add(19);
    associates.add(20);
    WorkOrder w = new WorkOrder(1, "01/02/20", "01/05/20", Category.LIGHTING, "Needs a new light bulb", "buhlakay@gmail.com", 1, 2);
    WorkOrder w1 = new WorkOrder(2, "01/02/20", "01/05/20", Category.LIGHTING, "Needs a new light bulb", "buhlakay@gmail.com", 1, 2);
    WorkOrder w2 = new WorkOrder(3, "01/02/20", "01/05/20", Category.LIGHTING, "Needs a new light bulb", "buhlakay@gmail.com", 1, 2);
    WorkOrder w3 = new WorkOrder(4, "01/02/20", "01/05/20", Category.LIGHTING, "Needs a new light bulb", "buhlakay@gmail.com", 1, 2);
    WorkOrder w4 = new WorkOrder(5, "01/02/20", "01/05/20", Category.LIGHTING, "Needs a new light bulb", "buhlakay@gmail.com", 1, 2);
    WorkOrder w5 = new WorkOrder(6, "01/02/20", "01/05/20", Category.LIGHTING, "Needs a new light bulb", "buhlakay@gmail.com", 1, 2);
    WorkOrder w6 = new WorkOrder(7, "01/02/20", "01/05/20", Category.LIGHTING, "Needs a new light bulb", "buhlakay@gmail.com", 1, 2);
    WorkOrder w7 = new WorkOrder(8, "01/02/20", "01/05/20", Category.LIGHTING, "Needs a new light bulb", "buhlakay@gmail.com", 1, 2);
    WorkOrder w8 = new WorkOrder(9, "01/02/20", "01/05/20", Category.LIGHTING, "Needs a new light bulb", "buhlakay@gmail.com", 1, 2);
    WorkOrder w9 = new WorkOrder(10, "01/02/20", "01/05/20", Category.LIGHTING, "Needs a new light bulb", "buhlakay@gmail.com", 1, 2);
    WorkOrder w10 = new WorkOrder(11, "01/02/20", "01/05/20", Category.LIGHTING, "Needs a new light bulb", "buhlakay@gmail.com", 1, 2);
    WorkOrder w11 = new WorkOrder(12, "01/02/20", "01/05/20", Category.LIGHTING, "Needs a new light bulb", "buhlakay@gmail.com", 1, 2);
    WorkOrder w12 = new WorkOrder(13, "01/02/20", "01/05/20", Category.LIGHTING, "Needs a new light bulb", "buhlakay@gmail.com", 1, 2);
    WorkOrder w13 = new WorkOrder(14, "01/02/20", "01/05/20", Category.LIGHTING, "Needs a new light bulb", "buhlakay@gmail.com", 1, 2);
    WorkOrder w14 = new WorkOrder(15, "01/02/20", "01/05/20", Category.LIGHTING, "Needs a new light bulb", "buhlakay@gmail.com", 1, 2);
    WorkOrder w15 = new WorkOrder(16, "01/02/20", "01/05/20", Category.LIGHTING, "Needs a new light bulb", "buhlakay@gmail.com", 1, 2);
    WorkOrder w16 = new WorkOrder(17, "01/02/20", "01/05/20", Category.LIGHTING, "Needs a new light bulb", "buhlakay@gmail.com", 1, 2);

    Batch b = new Batch(1, "191216-java-usf", "12/16/2019", "3/20/2020", 1, 0, associates, Curriculum.JAVA_MSA, resource);
    Batch b1 = new Batch(2, "200105-java-usf", "1/5/2020", "4/7/2020", 2, 0, associates, Curriculum.JAVA_MSA, resource);
    Batch b2 = new Batch(3, "191216-java-usf","12/16/2019","3/20/2020",3,0, associates, Curriculum.JAVA_MSA, resource);
    Batch b3 = new Batch(4, "191216-java-usf", "12/16/2019", "3/20/2020", 4, 0, associates, Curriculum.JAVA_MSA, resource);
    Batch b4 = new Batch(5, "200105-java-usf", "1/5/2020", "4/7/2020", 5, 0, associates, Curriculum.JAVA_MSA, resource);
    Batch b5 = new Batch(6, "191216-java-usf","12/16/2019","3/20/2020",6,0, associates, Curriculum.JAVA_MSA, resource);
    Batch b6 = new Batch(7, "191216-java-usf", "12/16/2019", "3/20/2020", 1, 0, associates, Curriculum.JAVA_MSA, resource);
    Batch b7 = new Batch(8, "200105-java-usf", "1/5/2020", "4/7/2020", 2, 0, associates, Curriculum.JAVA_MSA, resource);
    Batch b8 = new Batch(9, "191216-java-usf","12/16/2019","3/20/2020",3,0, associates, Curriculum.JAVA_MSA, resource);workOrderRepo.save(w);
    Batch b9 = new Batch(10, "191216-java-usf", "12/16/2019", "3/20/2020", 1, 0, associates, Curriculum.JAVA_MSA, resource);
    Batch b10 = new Batch(11, "200105-java-usf", "1/5/2020", "4/7/2020", 2, 0, associates, Curriculum.JAVA_MSA, resource);
    Batch b11 = new Batch(12, "191216-java-usf","12/16/2019","3/20/2020",3,0, associates, Curriculum.JAVA_MSA, resource);
    Batch b12 = new Batch(13, "191216-java-usf", "12/16/2019", "3/20/2020", 1, 0, associates, Curriculum.JAVA_MSA, resource);
    Batch b13 = new Batch(14, "200105-java-usf", "1/5/2020", "4/7/2020", 2, 0, associates, Curriculum.JAVA_MSA, resource);
    Batch b14 = new Batch(15, "191216-java-usf","12/16/2019","3/20/2020",3,0, associates, Curriculum.JAVA_MSA, resource);
    Batch b15 = new Batch(16, "200105-java-usf", "1/5/2020", "4/7/2020", 2, 0, associates, Curriculum.JAVA_MSA, resource);
    Batch b16 = new Batch(17, "191216-java-usf","12/16/2019","3/20/2020",3,0, associates, Curriculum.JAVA_MSA, resource);
    workOrderRepo.save(w);
    workOrderRepo.save(w1);
    workOrderRepo.save(w2);
    workOrderRepo.save(w3);
    workOrderRepo.save(w4);
    workOrderRepo.save(w5);
    workOrderRepo.save(w6);
    workOrderRepo.save(w7);
    workOrderRepo.save(w8);
    workOrderRepo.save(w9);
    workOrderRepo.save(w10);
    workOrderRepo.save(w11);
    workOrderRepo.save(w12);
    workOrderRepo.save(w13);
    workOrderRepo.save(w14);
    workOrderRepo.save(w15);
    workOrderRepo.save(w16);
    batchRepo.save(b);
    batchRepo.save(b1);
    batchRepo.save(b2);
    batchRepo.save(b3);
    batchRepo.save(b4);
    batchRepo.save(b5);
    batchRepo.save(b6);
    batchRepo.save(b7);
    batchRepo.save(b8);
    batchRepo.save(b9);
    batchRepo.save(b10);
    batchRepo.save(b11);
    batchRepo.save(b12);
    batchRepo.save(b13);
    batchRepo.save(b14);
    batchRepo.save(b15);
    batchRepo.save(b16);
  }
}
