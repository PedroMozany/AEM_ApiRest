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

import java.util.List;

@Model(adaptables = Resource.class)
public class Invoice {

    private int number;
    private int idProduct;
    private int idClient;
    private double value;

    public Invoice() {
    }


    public Invoice(int number, int idProduct, int idClient) {
        this.number = number;
        this.idProduct = idProduct;
        this.idClient = idClient;
    }

    public Invoice(int idProduct, int idClient) {
        this.idProduct = idProduct;
        this.idClient = idClient;
    }

    public int getNumber() {
        return number;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public int getIdClient() {
        return idClient;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NUMBER: " + number + "\n");
        sb.append("ID CLIENT: " + idClient +"\n");
        sb.append("ID PRODUCT: " + idProduct +"\n");
        sb.append("VALUE: " + value);
        return sb.toString();
    }
}
