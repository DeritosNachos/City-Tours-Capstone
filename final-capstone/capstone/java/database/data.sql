BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('JimJimIO','$2a$10$mSFs2NEDfW82aycQLCKTFup5GGAbVeb0u4Ba8hx46YcVy0gLu8wqW','ROLE_ADMIN');


INSERT INTO landmarks (landmark_name, landmark_photo, landmark_description, landmark_address, landmark_hours_of_operation, landmark_venue_type)
VALUES ('Liberty Bell', 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/17/7b/c2/7d/photo0jpg.jpg?w=1100&h=-1&s=1', 'The Liberty Bell, an enduring symbol of American independence and freedom, holds a significant place in the annals of history. Cast in 1752, this iconic bronze bell boasts a resounding tone that once echoed through the streets of Philadelphia. Its inscriptions, "Proclaim LIBERTY Throughout all the Land unto all the Inhabitants Thereof," resonate with the ideals upon which the United States was founded.', '526 Market St, Philadelphia, PA 19106', 'Monday - Saturday 9AM - 5PM', 'Park');

INSERT INTO landmarks (landmark_name, landmark_photo, landmark_description, landmark_address, landmark_hours_of_operation, landmark_venue_type)
VALUES ('Independence Hall', 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0b/98/81/20/photo1jpg.jpg?w=1100&h=-1&s=1', 'Independence Hall, located in Philadelphia, is an iconic historic building where both the Declaration of Independence and the United States Constitution were debated and adopted. This hallowed hall stands as a testament to the birth of American democracy, serving as a cherished symbol of liberty and self-governance.', '520 Chestnut Street, Philadelphia, PA 19106', 'Monday – Friday, 9 a.m. – 5 p.m.; Saturday & Sunday, 9 a.m. – 7 p.m.', 'Historic Landmark');

INSERT INTO landmarks (landmark_name, landmark_photo, landmark_description, landmark_address, landmark_hours_of_operation, landmark_venue_type)
VALUES ('Museum of the American Revolution', 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0e/83/80/0a/take-an-engrossing-chronologic.jpg?w=1100&h=-1&s=1', 'The Museum of the American Revolution, situated in Philadelphia, immerses visitors in the compelling narrative of America''s struggle for independence. Through interactive exhibits, artifacts, and immersive experiences, it brings to life the events and stories of the Revolutionary War era, fostering a deep understanding of this pivotal chapter in American history.', '101 South Third Street, Philadelphia, PA 19106', 'Daily 10AM - 5PM', 'Museum');

INSERT INTO landmarks (landmark_name, landmark_photo, landmark_description, landmark_address, landmark_hours_of_operation, landmark_venue_type)
VALUES ('Weitzman National Museum of American Jewish History', 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0f/da/e1/5c/nmajh-at-night.jpg?w=1100&h=-1&s=1', 'The Weitzman National Museum is a renowned institution dedicated to the celebration of art, culture, and history. Located in a striking architectural setting, it houses an extensive collection of masterpieces, exhibits, and educational programs that engage visitors and foster a deeper appreciation for the beauty and diversity of human creativity', '101 S. Independence Mall East, Philadelphia, PA 19106', 'Friday - Sunday 10AM - 5PM', 'Museum');

INSERT INTO itinerary (itinerary_name, itinerary_date, itinerary_starting_point) VALUES ('Philly Trip', '2023-06-09', '1400 John F Kennedy Blvd, Philadelphia, PA 19107');

INSERT INTO trips (user_id, itinerary_id) VALUES (3,1);

INSERT INTO destinations (itinerary_id, landmark_id) VALUES (1, 1);
INSERT INTO destinations (itinerary_id, landmark_id) VALUES (1, 2);



COMMIT TRANSACTION;
