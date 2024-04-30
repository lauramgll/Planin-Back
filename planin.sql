-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-04-2024 a las 13:44:23
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
-- Base de datos: `planin`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `icono` varchar(250) DEFAULT NULL,
  `color` varchar(7) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`, `icono`, `color`) VALUES
(1, 'Salario', NULL, '#86CE8E'),
(2, 'Alimentación', NULL, '#A7B5FE'),
(3, 'Gasolina', NULL, '#FFBC54'),
(4, 'Alquiler', NULL, '#FF8961'),
(5, 'Viajes', NULL, '#FFD45A'),
(6, 'Deporte', NULL, '#CE6EAE'),
(7, 'Ocio', NULL, '#71C7E2'),
(8, 'Ahorro', NULL, '#FFAA5F'),
(9, 'Suministros', NULL, '#999999'),
(10, 'Regalos', NULL, '#FFB0B9'),
(11, 'Inversiones', NULL, '#CD9AE8'),
(12, 'Transporte', NULL, '#8C9EDD'),
(13, 'Ropa', NULL, '#F7F396'),
(14, 'Salud', NULL, '#F792C8'),
(15, 'Mascotas', NULL, '#A8E2C9'),
(16, 'Donaciones', NULL, '#9B71CC'),
(17, 'Belleza', NULL, '#FF9292'),
(18, 'Otros', NULL, '#CECECE'),
(19, 'Transferencia', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas`
--

CREATE TABLE `cuentas` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `saldo` decimal(10,2) NOT NULL DEFAULT 0.00,
  `predeterminada` enum('Sí','No') NOT NULL DEFAULT 'Sí'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `cuentas`
--

INSERT INTO `cuentas` (`id`, `id_usuario`, `nombre`, `saldo`, `predeterminada`) VALUES
(1, 1, 'Principal', 0.00, 'Sí'),
(2, 1, 'Ahorro', 0.00, 'No'),
(3, 1, 'Inversión', 0.00, 'No'),
(4, 2, 'Corriente', 0.00, 'Sí'),
(5, 2, 'Negocio', 0.00, 'No');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transacciones`
--

CREATE TABLE `transacciones` (
  `id` int(11) NOT NULL,
  `id_cuenta` int(11) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `tipo` enum('ingreso','gasto','transferencia_origen','transferencia_destino') NOT NULL,
  `importe` decimal(10,2) NOT NULL DEFAULT 0.00,
  `notas` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `transacciones`
--

INSERT INTO `transacciones` (`id`, `id_cuenta`, `id_categoria`, `fecha`, `tipo`, `importe`, `notas`) VALUES
(1, 1, 1, '2024-04-17', 'ingreso', 2500.00, ''),
(2, 1, 2, '2024-04-16', 'gasto', 150.57, 'Mercadona'),
(3, 1, 3, '2024-04-15', 'gasto', 50.00, ''),
(4, 1, 4, '2024-04-14', 'gasto', 800.00, ''),
(5, 1, 5, '2024-04-13', 'gasto', 225.00, 'Compra vuelo Praga'),
(6, 1, 6, '2024-04-12', 'gasto', 50.00, 'Cuota gimnasio'),
(7, 1, 7, '2024-04-11', 'gasto', 32.85, 'Cena antiguos alumnos'),
(8, 1, 19, '2024-04-10', 'transferencia_origen', 300.00, 'Traspaso fondo emergencia'),
(9, 1, 9, '2024-04-09', 'gasto', 29.00, 'Internet y móvil'),
(10, 1, 10, '2024-04-08', 'gasto', 45.90, 'Regalo mamá'),
(11, 2, 19, '2024-04-10', 'transferencia_destino', 300.00, 'Traspaso fondo emergencia'),
(12, 1, 1, '2024-04-30', 'ingreso', 230.21, 'Comisiones abril'),
(13, 2, 8, '2024-04-26', 'ingreso', 150.00, 'Dinero cumple (abuelos)'),
(14, 2, 8, '2024-04-28', 'ingreso', 50.00, 'Dinero cumple (tía)'),
(15, 2, 4, '2024-04-05', 'ingreso', 650.00, 'Cobro alquiler chalet Bétera'),
(16, 2, 11, '2024-04-22', 'transferencia_origen', 600.00, 'Traspaso nueva inversión bolsa'),
(17, 3, 11, '2024-04-22', 'transferencia_destino', 600.00, 'Traspaso nueva inversión bolsa'),
(18, 3, 11, '2024-04-30', 'gasto', 34.66, 'Pérdidas acciones'),
(19, 3, 11, '2024-04-01', 'ingreso', 56.73, 'Ganancias fondos'),
(20, 4, 4, '2024-04-17', 'gasto', 430.00, ''),
(21, 4, 3, '2024-04-16', 'gasto', 15.00, 'Moto'),
(22, 4, 2, '2024-04-15', 'gasto', 200.19, 'Alcampo compra mes'),
(23, 4, 9, '2024-04-14', 'gasto', 80.00, 'Luz y agua abril'),
(24, 4, 15, '2024-04-13', 'gasto', 50.00, 'Vacuna y visita'),
(25, 4, 6, '2024-04-12', 'gasto', 90.00, 'Inscripción triatlón'),
(26, 4, 13, '2024-04-11', 'gasto', 105.99, ''),
(27, 4, 14, '2024-04-10', 'gasto', 12.50, 'Farmacia'),
(28, 4, 6, '2024-04-09', 'gasto', 30.00, 'Cuota gym'),
(29, 4, 12, '2024-04-08', 'gasto', 58.87, 'ITV Coche'),
(30, 4, 7, '2024-04-07', 'gasto', 17.40, 'Cine'),
(31, 4, 18, '2024-04-06', 'gasto', 46.95, 'Compra mesita noche SKLUM'),
(32, 4, 1, '2024-04-05', 'ingreso', 2710.64, ''),
(33, 4, 7, '2024-04-04', 'gasto', 6.00, 'Kebab'),
(34, 4, 19, '2024-04-03', 'transferencia_origen', 1000.00, 'Traspaso capital crecimiento negocio'),
(35, 5, 19, '2024-04-03', 'transferencia_destino', 1000.00, 'Traspaso capital crecimiento negocio'),
(36, 5, 9, '2024-04-30', 'gasto', 220.00, 'Agua y luz local'),
(37, 5, 18, '2024-04-01', 'ingreso', 121.00, 'Pago fra 24120'),
(38, 5, 18, '2024-04-01', 'ingreso', 605.00, 'Pago fra 24121'),
(39, 5, 18, '2024-04-01', 'ingreso', 266.20, 'Pago fra 24122'),
(40, 5, 18, '2024-04-30', 'gasto', 383.00, 'Seguridad Social'),
(41, 5, 18, '2024-04-01', 'ingreso', 60.50, 'Pago fra 24123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `vista_decimal` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `email`, `password`, `vista_decimal`) VALUES
(1, 'Laura', 'laura@planin.com', '1234567L', 1),
(2, 'Joan', 'joan@planin.com', '1234567J', 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_cuentas_usuario` (`id_usuario`) USING BTREE;

--
-- Indices de la tabla `transacciones`
--
ALTER TABLE `transacciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_transacciones_cuenta` (`id_cuenta`) USING BTREE,
  ADD KEY `fk_transacciones_categoria` (`id_categoria`) USING BTREE;

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `transacciones`
--
ALTER TABLE `transacciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuentas`
--
ALTER TABLE `cuentas`
  ADD CONSTRAINT `fk_cuentas_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `transacciones`
--
ALTER TABLE `transacciones`
  ADD CONSTRAINT `fk_transacciones_categoria` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_transacciones_cuenta` FOREIGN KEY (`id_cuenta`) REFERENCES `cuentas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
