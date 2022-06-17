INSERT INTO coffee_types ("id", "name", "needed_amount_of_water", "needed_amount_of_milk", "needed_amount_of_coffee_beans")
VALUES (1, 'Espresso', 0.030, 0.0, 0.018);
INSERT INTO coffee_types ("id", "name", "needed_amount_of_water", "needed_amount_of_milk", "needed_amount_of_coffee_beans")
VALUES (2, 'Cappuccino', 0.030, 0.60, 0.018);
INSERT INTO coffee_types ("id", "name", "needed_amount_of_water", "needed_amount_of_milk", "needed_amount_of_coffee_beans")
VALUES (3,'Latte', 0.030, 0.120, 0.018);
INSERT INTO coffee_types ("id", "name", "needed_amount_of_water", "needed_amount_of_milk", "needed_amount_of_coffee_beans")
VALUES (4,'Americano', 0.100, 0.0, 0.018);


INSERT INTO coffee_machine ("id", "status", "coffee_type_id", "amount_of_water", "amount_of_milk", "amount_of_coffee_beans")
VALUES (1, 'OFF', 1, 0.0, 0.0, 0.0);
