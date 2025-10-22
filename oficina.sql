-- phpMyAdmin SQL Dump (versão revisada)
-- Banco de dados: oficina
-- Estrutura sem vínculo entre pessoa e veiculo

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";
SET NAMES utf8mb4;

DROP DATABASE IF EXISTS `oficina`;
CREATE DATABASE `oficina` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `oficina`;

-- --------------------------------------------------------
-- Tabela: pessoa
-- --------------------------------------------------------
CREATE TABLE `pessoa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `endereco` varchar(150) DEFAULT NULL,
  `cpfcnpj` varchar(18) NOT NULL,
  `tipo` enum('F','J') NOT NULL DEFAULT 'F',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpfcnpj` (`cpfcnpj`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `pessoa` (`id`, `nome`, `telefone`, `endereco`, `cpfcnpj`, `tipo`) VALUES
(1, 'João Silva', '11988887777', 'Rua das Flores, 123', '123.456.789-00', 'F'),
(2, 'Maria Oliveira', '11999996666', 'Av. Paulista, 2000', '987.654.321-00', 'F'),
(3, 'Carlos Pereira', '11988881234', 'Rua das Laranjeiras, 45', '321.654.987-00', 'F'),
(4, 'Oficina TurboCar', '1133334444', 'Rua dos Mecânicos, 50', '12.345.678/0001-90', 'J'),
(5, 'Lucas Santos', '11955557777', 'Rua Verde, 85', '456.789.123-00', 'F');

-- --------------------------------------------------------
-- Tabela: veiculo (sem vínculo com pessoa)
-- --------------------------------------------------------
CREATE TABLE `veiculo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `marca` varchar(50) NOT NULL,
  `modelo` varchar(50) NOT NULL,
  `placa` varchar(10) NOT NULL,
  `ano` int(11) DEFAULT NULL,
  `cor` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `placa` (`placa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `veiculo` (`id`, `marca`, `modelo`, `placa`, `ano`, `cor`) VALUES
(1, 'Toyota', 'Corolla', 'ABC1A23', 2019, 'Prata'),
(2, 'Honda', 'Civic', 'DEF4B56', 2021, 'Preto'),
(3, 'Fiat', 'Argo', 'GHI7C89', 2020, 'Branco'),
(4, 'Volkswagen', 'Gol', 'JKL0D12', 2018, 'Vermelho'),
(5, 'Chevrolet', 'Onix', 'MNO3E45', 2022, 'Azul');

-- --------------------------------------------------------
-- Tabela: item
-- --------------------------------------------------------
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `marca` varchar(50) DEFAULT NULL,
  `valor` decimal(10,2) NOT NULL,
  `estoque` enum('N','S') NOT NULL DEFAULT 'S',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `item` (`id`, `nome`, `descricao`, `marca`, `valor`, `estoque`) VALUES
(1, 'Filtro de Óleo', 'Filtro para motor 1.6/2.0', 'Bosch', 35.00, 'S'),
(2, 'Pastilha de Freio', 'Jogo dianteiro', 'Fras-le', 120.00, 'S'),
(3, 'Óleo de Motor 5W30', 'Lubrificante sintético', 'Lubrax', 45.00, 'S'),
(4, 'Velas de Ignição', 'Jogo com 4 unidades', 'NGK', 80.00, 'S'),
(5, 'Amortecedor Dianteiro', 'Par dianteiro', 'Cofap', 350.00, 'S'),
(6, 'Correia Dentada', 'Para motores 1.0/1.6', 'Dayco', 150.00, 'S'),
(7, 'Filtro de Ar', 'Compatível com veículos 1.0 a 2.0', 'Mann', 40.00, 'S'),
(8, 'Lâmpada Farol H7', 'Farol alto/baixo', 'Philips', 30.00, 'S'),
(9, 'Bateria 60Ah', 'Livre de manutenção', 'Moura', 480.00, 'S'),
(10, 'Disco de Freio', 'Par dianteiro ventilado', 'TRW', 220.00, 'S');

-- --------------------------------------------------------
-- Tabela: ordemservico
-- --------------------------------------------------------
CREATE TABLE `ordemservico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cliente_id` int(11) NOT NULL,
  `veiculo_id` int(11) NOT NULL,
  `data_emissao` datetime DEFAULT current_timestamp(),
  `status` enum('ABERTA','FECHADA') NOT NULL DEFAULT 'ABERTA',
  PRIMARY KEY (`id`),
  KEY `cliente_id` (`cliente_id`),
  KEY `veiculo_id` (`veiculo_id`),
  CONSTRAINT `ordemservico_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `pessoa` (`id`)
  -- Removido vínculo de FK com veiculo
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `ordemservico` (`id`, `cliente_id`, `veiculo_id`, `data_emissao`, `status`) VALUES
(1, 1, 1, '2025-10-10 09:00:00', 'ABERTA'),
(2, 2, 2, '2025-10-09 14:30:00', 'FECHADA'),
(3, 3, 3, '2025-10-08 11:15:00', 'ABERTA'),
(4, 5, 5, '2025-10-07 10:00:00', 'FECHADA');

-- --------------------------------------------------------
-- Tabela: ordem_item
-- --------------------------------------------------------
CREATE TABLE `ordem_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ordem_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `quantidade` int(11) DEFAULT 1,
  `valor_unitario` decimal(10,2) DEFAULT NULL,
  `valor_total` decimal(10,2) GENERATED ALWAYS AS (`quantidade` * `valor_unitario`) STORED,
  PRIMARY KEY (`id`),
  KEY `ordem_id` (`ordem_id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `ordem_item_ibfk_1` FOREIGN KEY (`ordem_id`) REFERENCES `ordemservico` (`id`),
  CONSTRAINT `ordem_item_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `ordem_item` (`id`, `ordem_id`, `item_id`, `quantidade`, `valor_unitario`) VALUES
(1, 1, 1, 1, 35.00),
(2, 1, 3, 4, 45.00),
(3, 1, 4, 1, 80.00),
(4, 2, 2, 1, 120.00),
(5, 2, 10, 1, 220.00),
(6, 3, 5, 2, 350.00),
(7, 3, 6, 1, 150.00),
(8, 4, 7, 1, 40.00),
(9, 4, 9, 1, 480.00);

COMMIT;
