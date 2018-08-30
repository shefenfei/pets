package com.fenfei.pets.configs;

import com.fenfei.pets.springbatch.JobCompletionListener;
import com.fenfei.pets.springbatch.Processor;
import com.fenfei.pets.springbatch.Reader;
import com.fenfei.pets.springbatch.Writer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * springbatch config
 */
@Configuration
public class SpringBatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job processJob() {
        return jobBuilderFactory.get("processJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(orderStep1())
                .end()
                .build();
    }

    //核心配置
    @Bean
    public Step orderStep1() {
        return stepBuilderFactory.get("orderStep1").<String, String>chunk(1)
                .reader(new Reader())
                .processor(new Processor())
                .writer(new Writer())
                .build();
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionListener();
    }
}
