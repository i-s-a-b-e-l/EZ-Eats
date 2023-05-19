package com.eat.diet.repo.model;


import com.mongodb.client.FindIterable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Entity
@Document(collation = "food")
public class Food {
    @Id
    private String id;
    private String name;
    private int calories;
    private String type; //Breakfast, Lunch, or Dinner
    private boolean vegetarian;
    private boolean vegan;
    private boolean paleo;
    private boolean keto;
    private String recipe; // url to some recipe
    private String img;

    public Food(){
        name = "";
        calories = 0;
    }

    public static FindIterable<Document> find(Document document) {

    }
}

