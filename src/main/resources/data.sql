-- WYDAWCY
INSERT INTO publishers (name) VALUES ('Rebel'), ('Portal Games'), ('Galakta'), ('Phalanx'), ('Lucky Duck Games');

-- KATEGORIE
INSERT INTO categories (name) VALUES ('Strategiczna'), ('Ekonomiczna'), ('Przygodowa'), ('Rodzinna'), ('Imprezowa'), ('Karciana');

-- UŻYTKOWNICY
INSERT INTO users (username, email, membership_type) VALUES
                                                         ('jankowalski', 'jan@gmail.com', 'GOLD'),
                                                         ('annanowak', 'ania@op.pl', 'SILVER'),
                                                         ('piotr_p', 'piotrek@wp.pl', 'BRONZE'),
                                                         ('planszowa_bestia', 'bestia@gmail.com', 'GOLD'),
                                                         ('kasia_gry', 'katarzyna@interia.pl', 'SILVER'),
                                                         ('michal_u', 'michal@u.com', 'BRONZE'),
                                                         ('monika_z', 'moni@gmail.com', 'GOLD'),
                                                         ('tomek_t', 'tomek@wp.pl', 'SILVER'),
                                                         ('ola_v', 'ola@gmail.com', 'BRONZE'),
                                                         ('geek_master', 'master@bgg.com', 'GOLD');

-- GRY
INSERT INTO games (title, min_players, max_players, complexity_level, available, publisher_id, description) VALUES
                                                                                                                ('Catan', 3, 4, 2.3, true, 1, 'Klasyk gatunku - buduj osady i handluj surowcami.'),
                                                                                                                ('Terraformacja Marsa', 1, 5, 3.2, true, 2, 'Zmieniaj klimat Marsa w tej rozbudowanej strategii.'),
                                                                                                                ('Splendor', 2, 4, 1.8, true, 1, 'Zbieraj klejnoty i rezerwuj karty w wyścigu o punkty prestiżu.'),
                                                                                                                ('Dixit', 3, 6, 1.2, true, 1, 'Piękna gra skojarzeń i wyobraźni.'),
                                                                                                                ('7 Cudów Świata', 2, 7, 2.3, true, 2, 'Zbuduj cywilizację, która przetrwa wieki.'),
                                                                                                                ('Gloomhaven', 1, 4, 3.8, true, 3, 'Gigantyczna gra przygodowa w stylu legacy.'),
                                                                                                                ('Azul', 2, 4, 1.7, true, 5, 'Układaj piękne mozaiki na ścianach pałacu.'),
                                                                                                                ('Wsiąść do Pociągu: Europa', 2, 5, 1.9, true, 1, 'Buduj trasy kolejowe w całej Europie.'),
                                                                                                                ('Pandemic', 2, 4, 2.4, true, 3, 'Współpracuj, aby uratować świat przed epidemiami.'),
                                                                                                                ('Nemesis', 1, 5, 3.4, true, 2, 'Przetrwaj na statku opanowanym przez obcych.');

-- POŁĄCZENIE GIER Z KATEGORIAMI
INSERT INTO game_categories (game_id, category_id) VALUES
                                                       (1, 4), (1, 2),
                                                       (2, 1), (2, 2),
                                                       (3, 4), (3, 6),
                                                       (4, 4), (4, 5),
                                                       (5, 1), (5, 6),
                                                       (6, 1), (6, 3),
                                                       (7, 4),
                                                       (8, 4),
                                                       (9, 1),
                                                       (10, 3), (10, 1);

-- OCENY
INSERT INTO ratings (game_id, user_id, score) VALUES
                                                  (1, 1, 4), (1, 2, 5), (2, 4, 5), (2, 10, 5), (3, 3, 3), (10, 4, 5);