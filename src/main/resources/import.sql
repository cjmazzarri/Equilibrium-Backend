INSERT INTO commerces (created_at, updated_at, birth_date, email_address, first_name, last_name, password, username) VALUES ('2020-11-01 16:13:42', '2020-11-01 16:13:42', '2020-02-01 00:00:00', 'rparedes@gmail.com', 'Rosa', 'Paredes', '1234', 'RosaParedes'),('2020-11-01 16:13:42', '2020-11-01 16:13:42', '2020-02-02 00:00:00', 'ldiaz@gmail.com', 'Luis', 'Diaz', '1234', 'LuisDiaz'),('2020-11-01 16:13:42', '2020-11-01 16:13:42', '2020-02-03 00:00:00', 'jchavez@gmail.com', 'Jose', 'Chavez', '1234', 'JoseChavez'),('2020-11-01 16:13:42', '2020-11-01 16:13:42', '2020-02-04 00:00:00', 'lrevelo@gmail.com', 'Luis', 'Revelo', '1234', 'LuisRevelo');
INSERT INTO notifications (created_at, updated_at, content, commerce_id) VALUES('2020-11-01 16:22:37', '2020-11-01 16:22:37', 'rparedes notification1', '1'),('2020-11-01 16:22:37', '2020-11-01 16:22:37', 'rparedes notification2', '1'),('2020-11-01 16:22:37', '2020-11-01 16:22:37', 'ldiaz notification1', '2'),('2020-11-01 16:22:37', '2020-11-01 16:22:37', 'ldiaz notification2', '2'),('2020-11-01 16:22:37', '2020-11-01 16:22:37', 'jchavez notification1', '3'),('2020-11-01 16:22:37', '2020-11-01 16:22:37', 'jchavez notification2', '3'),('2020-11-01 16:22:37', '2020-11-01 16:22:37', 'lrevelo notification1', '4'),('2020-11-01 16:22:37', '2020-11-01 16:22:37', 'lrevelo notification2', '4');
INSERT INTO clients (created_at, updated_at, credit_amount, currency, first_name, last_name, commerce_id) VALUES('2020-11-01 16:25:04', '2020-11-01 16:25:04', '0', 's', 'Client1 de Rosa Paredes', 'LastName1', '1'),('2020-11-01 16:25:04', '2020-11-01 16:25:04', '0', 'd', 'Client2 de Rosa Paredes', 'LastName2', '1'),('2020-11-01 16:25:04', '2020-11-01 16:25:04', '0', 's', 'Client1 de Luis Diaz', 'LastName1', '2'),('2020-11-01 16:25:04', '2020-11-01 16:25:04', '0', 'd', 'Client2 de Luis Diaz', 'LastName2', '2'),('2020-11-01 16:25:04', '2020-11-01 16:25:04', '0', 's', 'Client1 de Jose Chavez', 'LastName1', '3'),('2020-11-01 16:25:04', '2020-11-01 16:25:04', '0', 'd', 'Client2 de Jose Chavez', 'LastName2', '3'),('2020-11-01 16:25:04', '2020-11-01 16:25:04', '0', 's', 'Client1 de Luis Revelo', 'LastName1', '4'),('2020-11-01 16:25:04', '2020-11-01 16:25:04', '0', 'd', 'Client2 de Luis Revelo', 'LastName2', '4');
INSERT INTO rates (id, created_at, updated_at, capitalization, period, type, value) VALUES('1', '2020-11-01 16:27:31', '2020-11-01 16:27:31', '', 'mensual', 'simple', '30'),('2', '2020-11-01 16:27:31', '2020-11-01 16:27:31', 'diario', 'quincenal', 'nominal', '10'),('3', '2020-11-01 16:27:31', '2020-11-01 16:27:31', '', 'anual', 'efectiva', '45'),('4', '2020-11-01 16:27:31', '2020-11-01 16:27:31', '', 'anual', 'efectiva', '45'),('5', '2020-11-01 16:27:31', '2020-11-01 16:27:31', '', 'mensual', 'simple', '30'),('6', '2020-11-01 16:27:31', '2020-11-01 16:27:31', 'diario', 'quincenal', 'nominal', '10'),('7', '2020-11-01 16:27:31', '2020-11-01 16:27:31', '', 'anual', 'efectiva', '45'),('8', '2020-11-01 16:27:31', '2020-11-01 16:27:31', '', 'anual', 'efectiva', '45');
INSERT INTO delivery_fees (id, created_at, updated_at, frequency, type, value) VALUES('1', '2020-11-01 16:32:10', '2020-11-01 16:32:10', '30', 'Periodo', '15'),('2', '2020-11-01 16:32:10', '2020-11-01 16:32:10', '0', 'Pedido', '15'),('3', '2020-11-01 16:32:10', '2020-11-01 16:32:10', '30', 'Periodo', '20'),('4', '2020-11-01 16:32:10', '2020-11-01 16:32:10', '0', 'Pedido', '10');
INSERT INTO maintenance_fees (id, created_at, updated_at, period, value) VALUES('1', '2020-11-01 16:37:08', '2020-11-01 16:37:08', 's', '10'),('2', '2020-11-01 16:37:08', '2020-11-01 16:37:08', 'q', '20'),('3', '2020-11-01 16:37:08', '2020-11-01 16:37:08', 'm', '40'),('4', '2020-11-01 16:37:08', '2020-11-01 16:37:08', 's', '25'),('5', '2020-11-01 16:37:08', '2020-11-01 16:37:08', 'q', '15'),('6', '2020-11-01 16:37:08', '2020-11-01 16:37:08', 'm', '30'),('7', '2020-11-01 16:37:08', '2020-11-01 16:37:08', 's', '25'),('8', '2020-11-01 16:37:08', '2020-11-01 16:37:08', 'q', '10');


