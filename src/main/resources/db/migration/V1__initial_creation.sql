CREATE TABLE IF NOT EXISTS job_companies
(
    company_id INT AUTO_INCREMENT PRIMARY KEY,
    company VARCHAR(255) NOT NULL UNIQUE
);

CREATE SEQUENCE job_companies_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS job_locations
(
    location_id INT AUTO_INCREMENT PRIMARY KEY,
    location VARCHAR(255) NOT NULL UNIQUE
);

CREATE SEQUENCE job_locations_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS job_tags
(
    tag_id INT AUTO_INCREMENT PRIMARY KEY,
    tag VARCHAR(255) NOT NULL UNIQUE
);

CREATE SEQUENCE job_tags_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS job_types
(
    type_id INT AUTO_INCREMENT PRIMARY KEY,
    type_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE SEQUENCE job_types_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS jobs
(
    job_id INT AUTO_INCREMENT PRIMARY KEY,
    slug VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR NOT NULL,
    remote BOOLEAN,
    url VARCHAR(255),
    created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tags
(
    job_id INT NOT NULL,
    tag_id INT NOT NULL,
    PRIMARY KEY (job_id, tag_id),
    FOREIGN KEY (job_id) REFERENCES jobs (job_id),
    FOREIGN KEY (tag_id) REFERENCES job_tags (tag_id)
);

CREATE TABLE IF NOT EXISTS types
(
    job_id INT NOT NULL,
    type_id INT NOT NULL,
    PRIMARY KEY (job_id, type_id),
    FOREIGN KEY (job_id) REFERENCES jobs (job_id),
    FOREIGN KEY (type_id) REFERENCES job_types (type_id)
);

CREATE TABLE IF NOT EXISTS companies
(
    job_id INT NOT NULL,
    company_id INT NOT NULL,
    PRIMARY KEY (job_id, company_id),
    FOREIGN KEY (job_id) REFERENCES jobs (job_id),
    FOREIGN KEY (company_id) REFERENCES job_companies (company_id)
);

CREATE TABLE IF NOT EXISTS locations
(
    job_id INT NOT NULL,
    location_id INT NOT NULL,
    PRIMARY KEY (job_id, location_id),
    FOREIGN KEY (job_id) REFERENCES jobs (job_id),
    FOREIGN KEY (location_id) REFERENCES job_locations (location_id)
);

-- ALTER TABLE jobs ADD FOREIGN KEY (tag_id) REFERENCES tags(tag_id);
-- ALTER TABLE jobs ADD FOREIGN KEY (type_id) REFERENCES types(type_id);
-- ALTER TABLE jobs ADD FOREIGN KEY (company_id) REFERENCES companies(company_id);
-- ALTER TABLE jobs ADD FOREIGN KEY (location_id) REFERENCES locations(location_id);