-- Limpiar tablas si es necesario (opcional para desarrollo)
DELETE FROM personas;
DELETE FROM usuarios;
DELETE FROM ciudades;
DELETE FROM provincias;
DELETE FROM generos;

-- Insertar Provincias del Ecuador
INSERT INTO provincias (id, nombre) VALUES
                                        (1, 'Azuay'),
                                        (2, 'Bolívar'),
                                        (3, 'Cañar'),
                                        (4, 'Carchi'),
                                        (5, 'Chimborazo'),
                                        (6, 'Cotopaxi'),
                                        (7, 'El Oro'),
                                        (8, 'Esmeraldas'),
                                        (9, 'Galápagos'),
                                        (10, 'Guayas'),
                                        (11, 'Imbabura'),
                                        (12, 'Loja'),
                                        (13, 'Los Ríos'),
                                        (14, 'Manabí'),
                                        (15, 'Morona Santiago'),
                                        (16, 'Napo'),
                                        (17, 'Orellana'),
                                        (18, 'Pastaza'),
                                        (19, 'Pichincha'),
                                        (20, 'Santa Elena'),
                                        (21, 'Santo Domingo de los Tsáchilas'),
                                        (22, 'Sucumbíos'),
                                        (23, 'Tungurahua'),
                                        (24, 'Zamora Chinchipe');

-- Insertar Ciudades principales por provincia
INSERT INTO ciudades (nombre, provincia_id) VALUES
-- Azuay (1)
('Cuenca', 1),
('Gualaceo', 1),
('Paute', 1),

-- Pichincha (19)
('Quito', 19),
('Sangolquí', 19),
('Cayambe', 19),

-- Guayas (10)
('Guayaquil', 10),
('Durán', 10),
('Samborondón', 10),

-- Otras provincias (ejemplos)
('Machala', 7),    -- El Oro
('Portoviejo', 14), -- Manabí
('Ambato', 23),     -- Tungurahua
('Loja', 12),       -- Loja
('Ibarra', 11),     -- Imbabura
('Tena', 16),       -- Napo
('Puerto Baquerizo Moreno', 9), -- Galápagos
('Nueva Loja', 22); -- Sucumbíos

-- Insertar Géneros
INSERT INTO generos (nombre) VALUES
                                 ('Masculino'),
                                 ('Femenino'),
                                 ('No binario'),
                                 ('Otro');
