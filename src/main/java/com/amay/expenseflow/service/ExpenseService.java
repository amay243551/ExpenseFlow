package com.amay.expenseflow.service;

import com.amay.expenseflow.dto.ExpenseRequestDTO;
import com.amay.expenseflow.dto.ExpenseResponseDTO;
import com.amay.expenseflow.entity.Expense;
import com.amay.expenseflow.exception.ExpenseNotFoundException;
import com.amay.expenseflow.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    // CREATE
    public ExpenseResponseDTO saveExpense(ExpenseRequestDTO requestDTO) {

        log.info("Saving expense: {}", requestDTO.getTitle());

        Expense expense = new Expense();

        expense.setTitle(requestDTO.getTitle());
        expense.setAmount(requestDTO.getAmount());
        expense.setCategory(requestDTO.getCategory());

        Expense savedExpense = expenseRepository.save(expense);

        log.info("Expense saved successfully with ID {}", savedExpense.getId());

        return new ExpenseResponseDTO(
                savedExpense.getId(),
                savedExpense.getTitle(),
                savedExpense.getAmount(),
                savedExpense.getCategory()
        );
    }

    // READ ALL
    public List<ExpenseResponseDTO> getAllExpenses() {

        log.info("Fetching all expenses");

        List<Expense> expenses = expenseRepository.findAll();

        return expenses.stream()
                .map(expense -> new ExpenseResponseDTO(
                        expense.getId(),
                        expense.getTitle(),
                        expense.getAmount(),
                        expense.getCategory()
                ))
                .toList();
    }

    // READ BY ID
    public ExpenseResponseDTO getExpenseById(Long id) {

        log.info("Fetching expense with ID {}", id);

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Expense not found with ID {}", id);
                    return new ExpenseNotFoundException("Expense not found with ID: " + id);
                });

        log.info("Expense found with ID {}", id);

        return new ExpenseResponseDTO(
                expense.getId(),
                expense.getTitle(),
                expense.getAmount(),
                expense.getCategory()
        );
    }

    // UPDATE
    public ExpenseResponseDTO updateExpense(Long id, ExpenseRequestDTO requestDTO) {

        log.info("Updating expense with ID {}", id);

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Expense not found with ID {}", id);
                    return new ExpenseNotFoundException("Expense not found with ID: " + id);
                });

        expense.setTitle(requestDTO.getTitle());
        expense.setAmount(requestDTO.getAmount());
        expense.setCategory(requestDTO.getCategory());

        Expense updatedExpense = expenseRepository.save(expense);

        log.info("Expense updated successfully with ID {}", updatedExpense.getId());

        return new ExpenseResponseDTO(
                updatedExpense.getId(),
                updatedExpense.getTitle(),
                updatedExpense.getAmount(),
                updatedExpense.getCategory()
        );
    }

    // DELETE
    public void deleteExpense(Long id) {

        log.info("Deleting expense with ID {}", id);

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Expense not found with ID {}", id);
                    return new ExpenseNotFoundException("Expense not found with ID: " + id);
                });

        expenseRepository.delete(expense);

        log.info("Expense deleted successfully with ID {}", id);
    }
}