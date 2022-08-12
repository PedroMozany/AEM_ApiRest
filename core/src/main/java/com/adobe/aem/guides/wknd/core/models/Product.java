/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class)
public class Product {

    private int id;
    private String nameProduct;
    private String category;
    private double price;

    public Product() {
    }

    public Product(int id, String nameProduct, String category, double price) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.category = category;
        this.price = price;
    }

    public Product(String nameProduct, String category, double price) {
        this.nameProduct = nameProduct;
        this.category = category;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n"+
                "NAME PRODUCT: " + nameProduct + "\n"+
                "CATEGORY: " + category + "\n" +
                "PRICE: " + price;
    }
}
