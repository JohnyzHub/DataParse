package com.jsonp.object.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class JsonPObjectReader {

	public static void main(String[] args) {
		JsonPObjectReader objectReader = new JsonPObjectReader();

		JsonObject jsonObject = objectReader.readJsonObjectFromFile();
		int id = Integer.valueOf(jsonObject.getString("@id"));
		String date = jsonObject.getString("@date");
		System.out.println("{@id - " + id + "@date - " + date + ", ");

		JsonObject customer = jsonObject.getJsonObject("customer");
		objectReader.readCustomer(customer);

		JsonObject content = jsonObject.getJsonObject("content");
		objectReader.readContent(content);

		JsonObject creditCard = jsonObject.getJsonObject("credit_card");
		objectReader.readCreditCart(creditCard);

	}

	public JsonObject readJsonObjectFromFile() {
		JsonObject jsonObject = null;
		File inputFile = Paths.get("src/main/resources/order.json").toFile();
		if (!inputFile.exists()) {
			System.out.println("order.json file doesn't exist");
			return jsonObject;
		}

		try (InputStream inStream = new FileInputStream(inputFile);
				JsonReader jsonReader = Json.createReader(inStream);) {
			jsonObject = jsonReader.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return jsonObject;
		}
		return jsonObject;
	}

	public void readCustomer(JsonObject customer) {
		String firstName = customer.getString("@first_name");
		String lastName = customer.getString("@last_name");
		String emailId = customer.getString("email");
		String phoneNumber = customer.getString("phoneNumber");
		System.out.println("Customer: {firstName - " + firstName + ", lastName - " + lastName + ", email - " + emailId
				+ ", phoneNumber - " + phoneNumber + "}, ");
	}

	public void readContent(JsonObject content) {
		JsonArray orderLineArray = content.getJsonArray("order_line");
		int arraySize = orderLineArray.size();
		List<String> orderLines = new ArrayList<String>(arraySize);
		for (int index = 0; index < arraySize; index++) {
			StringBuilder orderLineItem = new StringBuilder();
			if (index > 0) {
				orderLineItem.append(", ");
			}
			JsonObject orderLine = orderLineArray.getJsonObject(index);
			orderLineItem.append("{@item - " + orderLine.getString("@item"))
					.append(", @quantity - " + orderLine.getString("@quantity"))
					.append(", unit_price - " + orderLine.getString("unit_price") + "}");
			orderLines.add(orderLineItem.toString());
		}
		System.out.println("Content: {order_line:[" + orderLines + "]}, ");
	}

	public void readCreditCart(JsonObject creditCard) {
		int cardNumber = Integer.valueOf(creditCard.getString("@number"));
		String expiryDate = creditCard.getString("@expiry_date");
		int controlNumber = Integer.valueOf(creditCard.getString("@control_number"));
		String cardType = creditCard.getString("@type");
		System.out.println("credit_card: {" + cardNumber + "@number - " + cardNumber + ", @expiry_date - " + expiryDate
				+ ", @control_number" + controlNumber + ", @type" + cardType + "}}");
	}
}
