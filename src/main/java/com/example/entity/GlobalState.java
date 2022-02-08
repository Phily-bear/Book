package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GlobalState {
    int userCount;
    int bookCount;
    int borrowCount;
}
