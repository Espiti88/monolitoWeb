package com.unisabana.monolito.ordenes;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    int id;
    
    @NotNull
    int user_id;

    @NotNull
    int product_id;
}
