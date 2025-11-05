
CREATE TABLE IF NOT EXISTS payment_orders (
    id SERIAL PRIMARY KEY,
    external_id VARCHAR(50) NOT NULL,
    debtor_iban VARCHAR(34) NOT NULL,
    creditor_iban VARCHAR(34) NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    remittance_info VARCHAR(255),
    requested_execution_date DATE NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
