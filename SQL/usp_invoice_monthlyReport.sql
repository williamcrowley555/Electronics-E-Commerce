USE electronics

DELIMITER $$

DROP PROCEDURE IF EXISTS `usp_invoice_monthlyReport` $$
CREATE PROCEDURE `usp_invoice_monthlyReport`(
	IN month_in INT
)
BEGIN

	SELECT p.id, p.name, id.price, SUM(id.quantity) AS quantity, SUM(id.sub_total) AS sub_total
	FROM (electronics.invoice AS i JOIN electronics.invoice_details AS id ON i.id = id.invoice_id) 
		RIGHT JOIN electronics.product AS p ON id.product_id = p.id
	WHERE payment_date IS NOT NULL AND MONTH(payment_date) = month_in
	GROUP BY p.id, p.name, id.price;

END $$

DELIMITER ;