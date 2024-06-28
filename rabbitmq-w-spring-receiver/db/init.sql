CREATE TABLE payment (
    payment_id BINARY(16) PRIMARY KEY,
    payment_installment_index INT NOT NULL,
    payment_gross_total DECIMAL(10,2) NOT NULL,
    payment_due_date DATE NOT NULL,
    payment_type VARCHAR(20) NOT NULL,
    payment_interest_rate DECIMAL(10,2) NOT NULL,
    payment_financing_id VARCHAR(100) NOT NULL,
    payment_total_with_interest DECIMAL(10,2) NOT NULL
)