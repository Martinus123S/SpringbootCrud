package com.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document(Book.COLLECTION_NAME)
public class Book implements Serializable{

    public static final String COLLECTION_NAME = "book";

//    need description field
    private String bookId;
    private String title;
    private String description;
    private int totalPages;
    private int quantity;
    private String writer;
}
