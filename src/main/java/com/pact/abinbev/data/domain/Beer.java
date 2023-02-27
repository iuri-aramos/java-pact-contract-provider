package com.pact.abinbev.data.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(value = "beers")
public class Beer {
	@Id
	@Indexed
	private String id;
	@Field("name")
	private String name;
	@Field("brand")
	private String brand;
	@Field("price")
	private BigDecimal price;
	@Field("type")
	private String type;
}
