/**
 * CREATE Script for init of DB
 */

-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username, car_id) values (1, now(), false, 'OFFLINE',
'driver01pw', 'driver01', NULL);

insert into driver (id, date_created, deleted, online_status, password, username, car_id) values (2, now(), false, 'OFFLINE',
'driver02pw', 'driver02', NULL);

insert into driver (id, date_created, deleted, online_status, password, username, car_id) values (3, now(), false, 'OFFLINE',
'driver03pw', 'driver03', NULL);


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username, car_id) values (4, now(), false, 'ONLINE',
'driver04pw', 'driver04', NULL);

insert into driver (id, date_created, deleted, online_status, password, username, car_id) values (5, now(), false, 'ONLINE',
'driver05pw', 'driver05', NULL);

insert into driver (id, date_created, deleted, online_status, password, username, car_id) values (6, now(), false, 'ONLINE',
'driver06pw', 'driver06', NULL);

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username, car_id)
values
 (7,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'OFFLINE',
'driver07pw', 'driver07', NULL);

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username, car_id)
values
 (8,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'ONLINE',
'driver08pw', 'driver08', NULL);

-- Create 5 Cars

insert into car (id, date_created, license_plate, seat_count, convertible, color, rating, manufacturer, engine_type, deleted, model_year, category) 
VALUES(1, now(), 'ABC1234', 4, true, 'BLACK', 5, 'OPEL', 'GAS',false, 2020, 'LUXURY');

insert into car (id, date_created, license_plate, seat_count, convertible, color, rating, manufacturer, engine_type, deleted, model_year, category) 
VALUES(2, now(), 'ZYX0987', 4, false, 'WHITE', 5, 'MERCEDEZ', 'GAS',false, 2019, 'LARGE');

insert into car (id, date_created, license_plate, seat_count, convertible, color, rating, manufacturer, engine_type, deleted, model_year, category) 
VALUES(3, now(), 'FGH7293', 4, true, 'SILVER', 5, 'AUDI', 'GAS',false, 2018, 'REGULAR');

insert into car (id, date_created, license_plate, seat_count, convertible, color, rating, manufacturer, engine_type, deleted, model_year, category) 
VALUES(4, now(), 'LME7100', 4, false, 'RED', 5, 'TESLA', 'GAS',false, 2020, 'REGULAR');

insert into car (id, date_created, license_plate, seat_count, convertible, color, rating, manufacturer, engine_type, deleted, model_year, category) 
VALUES(5, now(), 'GAE2989', 4, true, 'BLUE', 5, 'VOLKSWAGEN', 'GAS',false, 2015, 'LARGE');