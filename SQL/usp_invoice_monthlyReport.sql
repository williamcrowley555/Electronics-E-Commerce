DELIMITER $$

DROP PROCEDURE IF EXISTS `usp_invoice_monthlyReport` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_invoice_monthlyReport`(
	IN month_in INT,
    IN year_in INT
)
BEGIN

	SELECT p.id AS product_id, p.name AS product_name, id.price AS product_price, SUM(id.quantity) AS total_quantity, SUM(id.sub_total) AS sub_total
	FROM (electronics.invoice AS i JOIN electronics.invoice_details AS id ON i.id = id.invoice_id) 
		RIGHT JOIN electronics.product AS p ON id.product_id = p.id
	WHERE payment_date IS NOT NULL AND MONTH(payment_date) = month_in AND YEAR(payment_date) = year_in
	GROUP BY p.id, p.name, id.price;

END $$

DELIMITER ;