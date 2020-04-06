INSERT INTO Haik.User (firstname, lastname, email, password, phone_number)
    VALUES
    ('Mirdon', 'Gashi', 'mirdon_g@hotmail.com', '123', '12345678');

INSERT INTO Haik.ride (created, startdate, starttime, createdbyid, seatsavailable, startlocation, destination, comments)
VALUES ('2020-01-01', '2020-01-02' , '10:30', '1', '3', 'Larvik sentrum', 'Oslo, st.haugen', 'Skal stoppe innom IKEA');



    INSERT INTO Haik.ride (created, createdById, startdate, starttime, seatsavailable, startlocation, destination, comments )
VALUES ('2020-03-19', '1', '2020-01-02', '10:00', '5', 'Ullevål Hageby', 'Stockholm', 'Obligatorisk pause på systembolaget'),
       ('2020-03-18', '2', '2020-12-02', '07:00', '3', 'Lillestrøm', 'Strømmen storsenter', 'Shopping'),
       ('2020-01-01', '1', '2021-10-10', '13:00', '1', 'Kværnerbyen', 'Nydalen', 'Graduation Academy!');
