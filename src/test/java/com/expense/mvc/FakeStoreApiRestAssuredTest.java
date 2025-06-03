package com.expense.mvc;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class FakeStoreApiRestAssuredTest {

    @Test
    public void testFakeStoreApiProducts() {
        // 1. Make GET request
        Response response = RestAssured.get("https://fakestoreapi.com/products");
        Assertions.assertEquals(200, response.getStatusCode(), "Expected HTTP 200 response");

        // 2. Parse response as List of Maps
        List<Map<String, Object>> products = response.jsonPath().getList("$");

        // 3. Validate products
        List<Map<String, Object>> defects = new ArrayList<>();
        for (Map<String, Object> product : products) {
            boolean hasDefect = false;
            StringBuilder defectReason = new StringBuilder();

            // Check title
            String title = (String) product.get("title");
            if (title == null || title.trim().isEmpty()) {
                hasDefect = true;
                defectReason.append("Empty title; ");
            }

            // Check price
            Number price = (Number) product.get("price");
            if (price == null || price.doubleValue() < 0) {
                hasDefect = true;
                defectReason.append("Negative price; ");
            }

            // Check rating.rate
            Map<String, Object> rating = (Map<String, Object>) product.get("rating");
            if (rating != null) {
                Number rate = (Number) rating.get("rate");
                if (rate != null && rate.doubleValue() > 5) {
                    hasDefect = true;
                    defectReason.append("Rating > 5; ");
                }
            }

            if (hasDefect) {
                defects.add(Map.of(
                    "title", title,
                    "price", price,
                    "rating", rating != null ? rating.get("rate") : null,
                    "reason", defectReason.toString()
                ));
            }
        }

        // 4. Print and assert
        if (!defects.isEmpty()) {
            System.out.println("Products with defects:");
            for (Map<String, Object> d : defects) {
                System.out.println(d);
            }
        }
        Assertions.assertTrue(defects.isEmpty(), "There are products with defects. See output above.");
    }
} 