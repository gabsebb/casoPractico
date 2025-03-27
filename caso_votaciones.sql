-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-03-2025 a las 06:05:13
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `caso_votaciones`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE `administrador` (
  `ID_ADMIN` int(11) NOT NULL,
  `APELLIDO` varchar(255) NOT NULL,
  `CARGO` varchar(255) DEFAULT NULL,
  `DEPARTAMENTO` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`ID_ADMIN`, `APELLIDO`, `CARGO`, `DEPARTAMENTO`, `NOMBRE`, `id_usuario`) VALUES
(1, 'Salazar', 'Jefazo', 'BOSS', 'Gabriel', 51);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudad`
--

CREATE TABLE `ciudad` (
  `ID_CIUDAD` int(11) NOT NULL,
  `NOMBRE` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `ciudad`
--

INSERT INTO `ciudad` (`ID_CIUDAD`, `NOMBRE`) VALUES
(1, 'Cuenca'),
(2, 'Guaranda'),
(3, 'Azogues'),
(4, 'Tulcán'),
(5, 'Riobamba'),
(6, 'Latacunga'),
(7, 'Machala'),
(8, 'Esmeraldas'),
(9, 'Puerto Baquerizo Moreno'),
(10, 'Guayaquil'),
(11, 'Ibarra'),
(12, 'Loja'),
(13, 'Babahoyo'),
(14, 'Portoviejo'),
(15, 'Macas'),
(16, 'Tena'),
(17, 'Puerto Francisco de Orellana'),
(18, 'Puyo'),
(19, 'Quito'),
(20, 'Santa Elena'),
(21, 'Santo Domingo'),
(22, 'Nueva Loja'),
(23, 'Ambato'),
(24, 'Zamora');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partidopol`
--

CREATE TABLE `partidopol` (
  `ID_PARTIDOPOL` int(11) NOT NULL,
  `NOMBRE` varchar(255) NOT NULL,
  `nombre_presidente` varchar(255) NOT NULL,
  `nombre_vice` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `partidopol`
--

INSERT INTO `partidopol` (`ID_PARTIDOPOL`, `NOMBRE`, `nombre_presidente`, `nombre_vice`) VALUES
(1, 'Acción Democrática Nacional ADN 7', 'Daniel Noboa', 'María José Pinto'),
(2, 'Revolución Ciudadana RC 5', 'Luisa González ', 'Diego Borja'),
(3, 'Pachakutik 18', 'Leónidas Iza', 'Katiuska Molina'),
(4, 'Partido Social Cristiano PSC 6', 'Henry Kronfle', 'Dallyana Passailaigue'),
(5, 'Izquierda Democrática ID 12', 'Carlos Rabascall', 'Alejandra Rivas'),
(6, 'Nulo', 'Nulo', 'Nulo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `provincia`
--

CREATE TABLE `provincia` (
  `ID_PROVINCIA` int(11) NOT NULL,
  `NOMBREPROVINCIA` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `provincia`
--

INSERT INTO `provincia` (`ID_PROVINCIA`, `NOMBREPROVINCIA`) VALUES
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `ID_USUARIO` int(11) NOT NULL,
  `CONTRASENIA` varchar(255) NOT NULL,
  `ROL` varchar(255) NOT NULL,
  `USUARIO` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`ID_USUARIO`, `CONTRASENIA`, `ROL`, `USUARIO`) VALUES
(1, '1712345678', 'VOTANTE', '1712345678'),
(2, '1723456789', 'VOTANTE', '1723456789'),
(3, '1734567890', 'VOTANTE', '1734567890'),
(4, '0912345678', 'VOTANTE', '0912345678'),
(5, '0923456789', 'VOTANTE', '0923456789'),
(6, '0934567890', 'VOTANTE', '0934567890'),
(7, '1012345678', 'VOTANTE', '1012345678'),
(8, '1023456789', 'VOTANTE', '1023456789'),
(9, '1034567890', 'VOTANTE', '1034567890'),
(10, '1719876543', 'VOTANTE', '1719876543'),
(11, '1728765432', 'VOTANTE', '1728765432'),
(12, '1737654321', 'VOTANTE', '1737654321'),
(13, '0918765432', 'VOTANTE', '0918765432'),
(14, '0927654321', 'VOTANTE', '0927654321'),
(15, '0936543210', 'VOTANTE', '0936543210'),
(16, '1019876543', 'VOTANTE', '1019876543'),
(17, '1028765432', 'VOTANTE', '1028765432'),
(18, '1037654321', 'VOTANTE', '1037654321'),
(19, '1711122334', 'VOTANTE', '1711122334'),
(20, '1722233445', 'VOTANTE', '1722233445'),
(21, '1733344556', 'VOTANTE', '1733344556'),
(22, '0911122334', 'VOTANTE', '0911122334'),
(23, '0922233445', 'VOTANTE', '0922233445'),
(24, '0933344556', 'VOTANTE', '0933344556'),
(25, '1011122334', 'VOTANTE', '1011122334'),
(26, '1022233445', 'VOTANTE', '1022233445'),
(27, '1033344556', 'VOTANTE', '1033344556'),
(28, '1714455667', 'VOTANTE', '1714455667'),
(29, '1725566778', 'VOTANTE', '1725566778'),
(30, '1736677889', 'VOTANTE', '1736677889'),
(31, '0914455667', 'VOTANTE', '0914455667'),
(32, '0925566778', 'VOTANTE', '0925566778'),
(33, '0936677889', 'VOTANTE', '0936677889'),
(34, '1014455667', 'VOTANTE', '1014455667'),
(35, '1025566778', 'VOTANTE', '1025566778'),
(36, '1036677889', 'VOTANTE', '1036677889'),
(37, '1717788990', 'VOTANTE', '1717788990'),
(38, '1728899001', 'VOTANTE', '1728899001'),
(39, '1739900112', 'VOTANTE', '1739900112'),
(40, '0917788990', 'VOTANTE', '0917788990'),
(41, '0928899001', 'VOTANTE', '0928899001'),
(42, '0939900112', 'VOTANTE', '0939900112'),
(43, '1017788990', 'VOTANTE', '1017788990'),
(44, '1028899001', 'VOTANTE', '1028899001'),
(45, '1039900112', 'VOTANTE', '1039900112'),
(46, '1710011223', 'VOTANTE', '1710011223'),
(47, '1721122334', 'VOTANTE', '1721122334'),
(48, '1732233445', 'VOTANTE', '1732233445'),
(49, '0910011223', 'VOTANTE', '0910011223'),
(50, '0921122334', 'VOTANTE', '0921122334'),
(51, '177013', 'ADMIN', 'admin'),
(52, '1714200639', 'VOTANTE', '1714200639'),
(53, 'adsa', 'VOTANTE', 'adsa'),
(54, '1763259018', 'VOTANTE', '1763259018');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `votante`
--

CREATE TABLE `votante` (
  `ID_VOTANTE` int(11) NOT NULL,
  `APELLIDO` varchar(255) NOT NULL,
  `CEDULA` varchar(255) NOT NULL,
  `CIUDADVOTANTE` varchar(255) NOT NULL,
  `GENEROVOTANTE` varchar(255) NOT NULL,
  `NOMBRE` varchar(255) NOT NULL,
  `PROVINCIAVOTANTE` varchar(255) NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `votante`
--

INSERT INTO `votante` (`ID_VOTANTE`, `APELLIDO`, `CEDULA`, `CIUDADVOTANTE`, `GENEROVOTANTE`, `NOMBRE`, `PROVINCIAVOTANTE`, `id_usuario`) VALUES
(1, 'Pérez', '1712345678', 'Quito', 'Masculino', 'Carlos', 'Pichincha', 1),
(2, 'Gómez', '1723456789', 'Guayaquil', 'Femenino', 'María', 'Guayas', 2),
(3, 'Rodríguez', '1734567890', 'Cuenca', 'Masculino', 'José', 'Azuay', 3),
(4, 'López', '0912345678', 'Ambato', 'Femenino', 'Ana', 'Tungurahua', 4),
(5, 'Martínez', '0923456789', 'Machala', 'Masculino', 'Luis', 'El Oro', 5),
(6, 'Hernández', '0934567890', 'Loja', 'Femenino', 'Lucía', 'Loja', 6),
(7, 'Díaz', '1012345678', 'Portoviejo', 'Masculino', 'Pedro', 'Manabí', 7),
(8, 'Torres', '1023456789', 'Riobamba', 'Femenino', 'Sofía', 'Chimborazo', 8),
(9, 'Flores', '1034567890', 'Esmeraldas', 'Masculino', 'Juan', 'Esmeraldas', 9),
(10, 'Vargas', '1719876543', 'Santo Domingo', 'Femenino', 'Patricia', 'Santo Domingo de los Tsáchilas', 10),
(11, 'Rojas', '1728765432', 'Ibarra', 'Masculino', 'Fernando', 'Imbabura', 11),
(12, 'Mendoza', '1737654321', 'Latacunga', 'Femenino', 'Gabriela', 'Cotopaxi', 12),
(13, 'Castro', '0918765432', 'Tena', 'Masculino', 'Roberto', 'Napo', 13),
(14, 'Ortega', '0927654321', 'Puerto Baquerizo Moreno', 'Femenino', 'Carmen', 'Galápagos', 14),
(15, 'Silva', '0936543210', 'Puyo', 'Masculino', 'Diego', 'Pastaza', 15),
(16, 'Núñez', '1019876543', 'Macas', 'Femenino', 'Valeria', 'Morona Santiago', 16),
(17, 'Jiménez', '1028765432', 'Zamora', 'Masculino', 'Andrés', 'Zamora Chinchipe', 17),
(18, 'Paz', '1037654321', 'Tulcán', 'Femenino', 'Daniela', 'Carchi', 18),
(19, 'Córdova', '1711122334', 'Guayaquil', 'Masculino', 'Jorge', 'Guayas', 19),
(20, 'Salazar', '1722233445', 'Quito', 'Femenino', 'Isabel', 'Pichincha', 20),
(21, 'Morales', '1733344556', 'Cuenca', 'Masculino', 'Ricardo', 'Azuay', 21),
(22, 'Vega', '0911122334', 'Ambato', 'Femenino', 'Carolina', 'Tungurahua', 22),
(23, 'Paredes', '0922233445', 'Machala', 'Masculino', 'Mario', 'El Oro', 23),
(24, 'Santana', '0933344556', 'Loja', 'Femenino', 'Paulina', 'Loja', 24),
(25, 'Carrillo', '1011122334', 'Portoviejo', 'Masculino', 'Eduardo', 'Manabí', 25),
(26, 'Orellana', '1022233445', 'Riobamba', 'Femenino', 'Verónica', 'Chimborazo', 26),
(27, 'Franco', '1033344556', 'Esmeraldas', 'Masculino', 'Hugo', 'Esmeraldas', 27),
(28, 'Valdez', '1714455667', 'Santo Domingo', 'Femenino', 'Natalia', 'Santo Domingo de los Tsáchilas', 28),
(29, 'Campos', '1725566778', 'Ibarra', 'Masculino', 'Oscar', 'Imbabura', 29),
(30, 'Guerrero', '1736677889', 'Latacunga', 'Femenino', 'Ximena', 'Cotopaxi', 30),
(31, 'Reyes', '0914455667', 'Tena', 'Masculino', 'Pablo', 'Napo', 31),
(32, 'Aguirre', '0925566778', 'Puerto Baquerizo Moreno', 'Femenino', 'Rosa', 'Galápagos', 32),
(33, 'Peña', '0936677889', 'Puyo', 'Masculino', 'Gustavo', 'Pastaza', 33),
(34, 'Mejía', '1014455667', 'Macas', 'Femenino', 'Silvia', 'Morona Santiago', 34),
(35, 'Cáceres', '1025566778', 'Zamora', 'Masculino', 'Felipe', 'Zamora Chinchipe', 35),
(36, 'Zambrano', '1036677889', 'Tulcán', 'Femenino', 'Lorena', 'Carchi', 36),
(37, 'Rosales', '1717788990', 'Guayaquil', 'Masculino', 'Raúl', 'Guayas', 37),
(38, 'Vera', '1728899001', 'Quito', 'Femenino', 'Adriana', 'Pichincha', 38),
(39, 'Rivera', '1739900112', 'Cuenca', 'Masculino', 'Mauricio', 'Azuay', 39),
(40, 'Ortiz', '0917788990', 'Ambato', 'Femenino', 'Claudia', 'Tungurahua', 40),
(41, 'Mora', '0928899001', 'Machala', 'Masculino', 'Arturo', 'El Oro', 41),
(42, 'Ávila', '0939900112', 'Loja', 'Femenino', 'Gladys', 'Loja', 42),
(43, 'Palacios', '1017788990', 'Portoviejo', 'Masculino', 'Wilson', 'Manabí', 43),
(44, 'Salinas', '1028899001', 'Riobamba', 'Femenino', 'Rocío', 'Chimborazo', 44),
(45, 'Cruz', '1039900112', 'Esmeraldas', 'Masculino', 'Alex', 'Esmeraldas', 45),
(46, 'Miranda', '1710011223', 'Santo Domingo', 'Femenino', 'Elena', 'Santo Domingo de los Tsáchilas', 46),
(47, 'Carvajal', '1721122334', 'Ibarra', 'Masculino', 'Héctor', 'Imbabura', 47),
(48, 'Gallegos', '1732233445', 'Latacunga', 'Femenino', 'Mónica', 'Cotopaxi', 48),
(49, 'León', '0910011223', 'Tena', 'Masculino', 'Vinicio', 'Napo', 49),
(50, 'Figueroa', '0921122334', 'Puerto Baquerizo Moreno', 'Femenino', 'Esther', 'Galápagos', 50),
(51, 'Reinoso', '1714200639', 'Quito', 'Femenino', 'Veronica', 'Pichincha', 52),
(52, 'asd', 'adsa', '', '', 'saddas', 'asda', 53),
(53, 'de Fuego', '1763259018', 'Quito', 'Masculino', 'Tilin', 'Pichincha', 54);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `voto`
--

CREATE TABLE `voto` (
  `ID_VOTO` int(11) NOT NULL,
  `FECHA_VOTO` datetime DEFAULT NULL,
  `id_partidoPol` int(11) NOT NULL,
  `id_votante` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `voto`
--

INSERT INTO `voto` (`ID_VOTO`, `FECHA_VOTO`, `id_partidoPol`, `id_votante`) VALUES
(2, '2025-03-26 16:45:36', 2, 2),
(3, '2025-03-26 16:45:36', 5, 3),
(4, '2025-03-26 16:45:36', 4, 4),
(5, '2025-03-26 21:37:54', 2, 1),
(6, '2025-03-26 16:45:36', 3, 6),
(7, '2025-03-26 16:45:36', 2, 7),
(8, '2025-03-26 16:45:36', 1, 8),
(9, '2025-03-26 16:45:36', 3, 9),
(10, '2025-03-26 16:45:36', 1, 10),
(11, '2025-03-26 16:45:36', 3, 11),
(12, '2025-03-26 16:45:36', 1, 12),
(13, '2025-03-26 16:45:36', 4, 13),
(14, '2025-03-26 16:45:36', 2, 14),
(15, '2025-03-26 16:45:36', 1, 15),
(16, '2025-03-26 16:45:36', 1, 16),
(17, '2025-03-26 16:45:36', 5, 17),
(18, '2025-03-26 16:45:36', 1, 18),
(19, '2025-03-26 16:45:36', 3, 19),
(20, '2025-03-26 16:45:36', 5, 20),
(21, '2025-03-26 16:45:36', 3, 21),
(22, '2025-03-26 16:45:36', 1, 22),
(23, '2025-03-26 16:45:36', 2, 23),
(24, '2025-03-26 16:45:36', 4, 24),
(25, '2025-03-26 16:45:36', 4, 25),
(26, '2025-03-26 16:45:36', 3, 26),
(27, '2025-03-26 16:45:36', 3, 27),
(28, '2025-03-26 16:45:36', 4, 28),
(29, '2025-03-26 16:45:36', 1, 29),
(30, '2025-03-26 16:45:36', 1, 30),
(31, '2025-03-26 16:45:36', 1, 31),
(32, '2025-03-26 16:45:36', 2, 32),
(33, '2025-03-26 16:45:36', 4, 33),
(34, '2025-03-26 16:45:36', 1, 34),
(35, '2025-03-26 16:45:36', 2, 35),
(36, '2025-03-26 16:45:36', 2, 36),
(37, '2025-03-26 16:45:36', 1, 37),
(38, '2025-03-26 16:45:36', 5, 38),
(39, '2025-03-26 16:45:36', 2, 39),
(40, '2025-03-26 16:45:36', 2, 40),
(41, '2025-03-26 16:45:36', 5, 41),
(42, '2025-03-26 16:45:36', 1, 42),
(43, '2025-03-26 16:45:36', 4, 43),
(44, '2025-03-26 16:45:36', 2, 44),
(45, '2025-03-26 16:45:36', 4, 45),
(46, '2025-03-26 16:45:36', 1, 46),
(47, '2025-03-26 16:45:36', 2, 47),
(48, '2025-03-26 16:45:36', 4, 48),
(49, '2025-03-26 16:45:36', 2, 49),
(50, '2025-03-26 16:45:36', 4, 50),
(51, '2025-03-26 23:16:25', 2, 51);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`ID_ADMIN`),
  ADD UNIQUE KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD PRIMARY KEY (`ID_CIUDAD`);

--
-- Indices de la tabla `partidopol`
--
ALTER TABLE `partidopol`
  ADD PRIMARY KEY (`ID_PARTIDOPOL`),
  ADD UNIQUE KEY `NOMBRE` (`NOMBRE`);

--
-- Indices de la tabla `provincia`
--
ALTER TABLE `provincia`
  ADD PRIMARY KEY (`ID_PROVINCIA`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`ID_USUARIO`),
  ADD UNIQUE KEY `USUARIO` (`USUARIO`);

--
-- Indices de la tabla `votante`
--
ALTER TABLE `votante`
  ADD PRIMARY KEY (`ID_VOTANTE`),
  ADD UNIQUE KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `voto`
--
ALTER TABLE `voto`
  ADD PRIMARY KEY (`ID_VOTO`),
  ADD UNIQUE KEY `id_votante` (`id_votante`),
  ADD KEY `FK_VOTO_id_partidoPol` (`id_partidoPol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `administrador`
--
ALTER TABLE `administrador`
  MODIFY `ID_ADMIN` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  MODIFY `ID_CIUDAD` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `partidopol`
--
ALTER TABLE `partidopol`
  MODIFY `ID_PARTIDOPOL` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `provincia`
--
ALTER TABLE `provincia`
  MODIFY `ID_PROVINCIA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `ID_USUARIO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT de la tabla `votante`
--
ALTER TABLE `votante`
  MODIFY `ID_VOTANTE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT de la tabla `voto`
--
ALTER TABLE `voto`
  MODIFY `ID_VOTO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD CONSTRAINT `FK_ADMINISTRADOR_id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`ID_USUARIO`);

--
-- Filtros para la tabla `votante`
--
ALTER TABLE `votante`
  ADD CONSTRAINT `FK_VOTANTE_id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`ID_USUARIO`);

--
-- Filtros para la tabla `voto`
--
ALTER TABLE `voto`
  ADD CONSTRAINT `FK_VOTO_id_partidoPol` FOREIGN KEY (`id_partidoPol`) REFERENCES `partidopol` (`ID_PARTIDOPOL`),
  ADD CONSTRAINT `FK_VOTO_id_votante` FOREIGN KEY (`id_votante`) REFERENCES `votante` (`ID_VOTANTE`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
