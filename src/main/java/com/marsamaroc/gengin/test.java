package com.marsamaroc.gengin;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class test {
        @Id

        private int test;
        private String testS;
    }

