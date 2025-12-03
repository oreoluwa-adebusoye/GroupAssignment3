-- Create the service table
CREATE TABLE service (
    service_id INT NOT NULL AUTO_INCREMENT,
    service_name VARCHAR(50) NOT NULL,
    service_type VARCHAR(50) NOT NULL,
    description VARCHAR(200),
    base_price REAL NOT NULL,
    estimated_duration INT NOT NULL,
    CONSTRAINT service_pk PRIMARY KEY (service_id)
);

-- Insert Services
insert into service(service_name, service_type, description, base_price, estimated_duration) 
values ('Wellness Exam', 'Medical', 'Complete physical examination', 75.00, 30);

insert into service(service_name, service_type, description, base_price, estimated_duration) 
values ('Dental Cleaning', 'Medical', 'Professional teeth cleaning under anesthesia', 350.00, 90);

insert into service(service_name, service_type, description, base_price, estimated_duration) 
values ('Spay Surgery', 'Surgical', 'Ovariohysterectomy procedure for female pets', 261.32, 90);

insert into service(service_name, service_type, description, base_price, estimated_duration) 
values ('Vaccination', 'Preventive', 'Administration of vaccines', 38.00, 15);
