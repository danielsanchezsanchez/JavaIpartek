-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-06-2017 a las 20:00:53
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `comercioddbb`
--
CREATE DATABASE IF NOT EXISTS `comercioddbb` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `comercioddbb`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura-productos`
--

DROP TABLE IF EXISTS `factura-productos`;
CREATE TABLE `factura-productos` (
  `id_facturas` int(11) NOT NULL,
  `id_productos` int(11) NOT NULL,
  `cantidad` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `factura-productos`
--

INSERT INTO `factura-productos` (`id_facturas`, `id_productos`, `cantidad`) VALUES
(2, 1, 3),
(5, 6, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

DROP TABLE IF EXISTS `facturas`;
CREATE TABLE `facturas` (
  `id` int(11) NOT NULL,
  `numero_factura` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha_compra` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `facturas`
--

INSERT INTO `facturas` (`id`, `numero_factura`, `id_usuario`, `fecha_compra`) VALUES
(2, 'DENDA0001', 1, '2017-06-20 14:42:58'),
(3, 'DENDA0002', 8, '2017-06-20 14:43:20'),
(4, 'DENDA0003', 2, '2017-06-20 14:43:27'),
(5, 'DENDA0007', 2, '2017-06-20 16:19:09'),
(9, 'DENDA00008', 6, '2017-06-20 16:28:07'),
(10, 'DENDA0009', 8, '2017-06-20 17:01:49');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

DROP TABLE IF EXISTS `productos`;
CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(80) COLLATE utf8_spanish_ci NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `descripcion` text COLLATE utf8_spanish_ci NOT NULL,
  `url_producto_img` varchar(40) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `nombre`, `precio`, `descripcion`, `url_producto_img`) VALUES
(1, 'Mesa', '50.30', 'Mesa cuadrada bonita.', NULL),
(4, 'Silla', '20.00', 'Silla redonda feA.', NULL),
(6, 'DFGFDGDFG', '20.00', 'FGHFGHFGHGHG', NULL),
(7, 'Cuaderno', '7.23', 'Cuaderno de anillas pro', '.......................'),
(8, 'Boligrafo', '2.50', 'Boli bic ', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `rol` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` text COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `rol`, `descripcion`) VALUES
(1, 'admin', 'Permisos de todo tipo, en cuanto a la modificación del programa.\r\nNo puede realizar compras.'),
(2, 'cliente', 'Permisos de compra, devoluciones y reclamaciones.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL,
  `nickusuario` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `apellido1` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `apellido2` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `contrasenia` varchar(80) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `id_rol`, `nickusuario`, `nombre`, `apellido1`, `apellido2`, `contrasenia`) VALUES
(1, 1, 'admin', 'admin-name', 'admin-name', 'admin-name', 'pass'),
(2, 2, 'gudy20', 'Daniel', 'Sanchez', 'Sanchez', 'sua20'),
(6, 2, 'javitxu', 'javier', 'lete', 'lete2', 'letazo'),
(7, 2, 'troll', 'trolete', 'trolero', 'trolazo', 'troll'),
(8, 2, 'danielete', 'daniel', 'sanchez', 'sanchez', 'iratxe12345'),
(9, 2, 'WEI20', 'manolo', 'txurrus', 'txurrus', '12345678');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `factura-productos`
--
ALTER TABLE `factura-productos`
  ADD KEY `id_facturas` (`id_facturas`),
  ADD KEY `id_productos` (`id_productos`);

--
-- Indices de la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `iu_numero_factura` (`numero_factura`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `iu_nombre` (`nombre`),
  ADD UNIQUE KEY `iu_url_producto_img` (`url_producto_img`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `n_rol` (`rol`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `iu_nickusuario` (`nickusuario`),
  ADD KEY `i_rol` (`id_rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `facturas`
--
ALTER TABLE `facturas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `factura-productos`
--
ALTER TABLE `factura-productos`
  ADD CONSTRAINT `factura-productos_ibfk_1` FOREIGN KEY (`id_facturas`) REFERENCES `facturas` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `factura-productos_ibfk_2` FOREIGN KEY (`id_productos`) REFERENCES `productos` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD CONSTRAINT `facturas_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `fk_roles` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
