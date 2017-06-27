-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-06-2017 a las 20:02:24
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
(1, 'DENDA00000001', 2, '2017-06-23 17:53:21'),
(2, 'DENDA00000002', 2, '2017-06-26 12:54:47'),
(3, 'DENDA00000003', 2, '2017-06-26 13:04:11'),
(4, 'DENDA00000004', 7, '2017-06-26 13:25:29'),
(5, 'DENDA00000005', 7, '2017-06-26 13:26:02'),
(6, 'DENDA00000006', 7, '2017-06-26 13:36:58'),
(7, 'DENDA00000007', 2, '2017-06-26 13:38:25'),
(8, 'DENDA00000008', 6, '2017-06-26 13:42:02'),
(9, 'DENDA00000009', 6, '2017-06-26 13:46:37'),
(10, 'DENDA00000010', 13, '2017-06-26 15:14:59');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura_productos`
--

DROP TABLE IF EXISTS `factura_productos`;
CREATE TABLE `factura_productos` (
  `id_factura` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci ROW_FORMAT=COMPACT;

--
-- Volcado de datos para la tabla `factura_productos`
--

INSERT INTO `factura_productos` (`id_factura`, `id_producto`, `cantidad`) VALUES
(1, 1, 6),
(4, 1, 4),
(4, 6, 0),
(4, 13, 0),
(5, 4, 2),
(5, 7, 2),
(6, 1, 3),
(6, 6, 2),
(7, 1, 4),
(7, 6, 3),
(8, 1, 5),
(8, 4, 4),
(8, 7, 4),
(9, 1, 4),
(9, 7, 3),
(10, 4, 1),
(10, 6, 2);

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
(6, 'Auriculares', '2.10', 'Blancos y negros', ''),
(7, 'Cuaderno', '7.23', 'Cuaderno de anillas pro', ''),
(8, 'Boligrafo', '2.50', 'Boli bic ', ''),
(13, 'Sillon', '62.30', 'Masajeador opcional.', '');

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
-- Estructura de tabla para la tabla `stock`
--

DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `id_producto` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `entienda` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `stock`
--

INSERT INTO `stock` (`id_producto`, `stock`, `entienda`) VALUES
(1, 200, 1),
(7, 100, 1),
(8, 500, 1),
(13, 500, 1);

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
(8, 2, 'danielito', 'daniel', 'sanchez', 'sanchez', 'iratxe12345'),
(10, 1, 'admin2', 'admin2', 'admin2', 'admin2', 'pass2'),
(11, 2, 'DANILOLU', 'capullo', 'mayor', 'capullinson', '123456'),
(13, 2, 'mikel', 'mikel', 'mikel', 'mikel', 'mikel');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `iu_numero_factura` (`numero_factura`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `factura_productos`
--
ALTER TABLE `factura_productos`
  ADD KEY `id_facturas` (`id_factura`),
  ADD KEY `id_productos` (`id_producto`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `iu_nombre` (`nombre`);
ALTER TABLE `productos` ADD FULLTEXT KEY `nombre` (`nombre`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `n_rol` (`rol`);

--
-- Indices de la tabla `stock`
--
ALTER TABLE `stock`
  ADD KEY `id_producto` (`id_producto`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD CONSTRAINT `facturas_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `factura_productos`
--
ALTER TABLE `factura_productos`
  ADD CONSTRAINT `factura_productos_ibfk_1` FOREIGN KEY (`id_factura`) REFERENCES `facturas` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `factura_productos_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `stock`
--
ALTER TABLE `stock`
  ADD CONSTRAINT `stock_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `fk_roles` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
