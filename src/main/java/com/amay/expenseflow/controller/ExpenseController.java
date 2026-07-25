package com.amay.expenseflow.controller;

import com.amay.expenseflow.dto.ExpenseRequestDTO;
import com.amay.expenseflow.dto.ExpenseResponseDTO;
import com.amay.expenseflow.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    // CREATE
    @PostMapping
    public ResponseEntity<ExpenseResponseDTO> saveExpense(
            @Valid @RequestBody ExpenseRequestDTO requestDTO) {

        ExpenseResponseDTO responseDTO =
                expenseService.saveExpense(requestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<ExpenseResponseDTO>> getAllExpenses() {

        List<ExpenseResponseDTO> expenses =
                expenseService.getAllExpenses();

        return ResponseEntity.ok(expenses);
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> getExpenseById(
            @PathVariable Long id) {

        ExpenseResponseDTO expense =
                expenseService.getExpenseById(id);

        return ResponseEntity.ok(expense);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody ExpenseRequestDTO requestDTO) {

        ExpenseResponseDTO updatedExpense =
                expenseService.updateExpense(id, requestDTO);

        return ResponseEntity.ok(updatedExpense);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(
            @PathVariable Long id) {

        expenseService.deleteExpense(id);

        return ResponseEntity.noContent().build();
    }
}