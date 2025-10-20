-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 24, 2024 at 06:24 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `obp`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, '825230127', 'leny1375');

-- --------------------------------------------------------

--
-- Table structure for table `hasil_voting`
--

CREATE TABLE `hasil_voting` (
  `id` bigint(20) NOT NULL,
  `jumlah_suara` bigint(20) DEFAULT NULL,
  `nama_kandidat` varchar(255) DEFAULT NULL,
  `persentase` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hasil_voting`
--

INSERT INTO `hasil_voting` (`id`, `jumlah_suara`, `nama_kandidat`, `persentase`) VALUES
(1, 2, 'STEFANI VIRANESA & RIVAN ANGGARA', 50),
(2, 1, 'DIRGANTARA JOSHUA & REGINA YASMIN', 25),
(3, 1, 'JONATHAN XALENDRA & ADINDA NAILAH', 25);

-- --------------------------------------------------------

--
-- Table structure for table `kandidat`
--

CREATE TABLE `kandidat` (
  `id` bigint(20) NOT NULL,
  `visi` varchar(255) DEFAULT NULL,
  `misi` varchar(512) DEFAULT NULL,
  `slogan_kandidat` varchar(255) DEFAULT NULL,
  `foto_kandidat` varchar(255) DEFAULT NULL,
  `nama_kandidat` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kandidat`
--

INSERT INTO `kandidat` (`id`, `visi`, `misi`, `slogan_kandidat`, `foto_kandidat`, `nama_kandidat`) VALUES
(1, '\"Mewujudkan BEM sebagai organisasi mahasiswa yang responsif, inspiratif, dan berdampak positif bagi seluruh sivitas kampus dan masyarakat luas.\"', 'Menyuarakan aspirasi mahasiswa dengan transparansi, integritas, dan komitmen dalam memberikan solusi terbaik.\n\nMenginisiasi kegiatan yang mendukung pengembangan soft skill dan leadership bagi mahasiswa.\n\nMenggalang program-program sosial yang melibatkan mahasiswa untuk memberikan kontribusi nyata kepada masyarakat.', 'Bersama Kami, Suara Mahasiswa Didengar, Aspirasi Terwujud!', 'pic1.jpg', 'STEFANI VIRANESA & RIVAN ANGGARA'),
(2, '\"Menjadikan BEM sebagai wadah pengembangan karakter dan kompetensi mahasiswa yang berlandaskan kolaborasi, inovasi, dan kepedulian sosial.\"\r\n', 'Mengembangkan program-program kreatif dan inovatif yang bermanfaat bagi mahasiswa dan masyarakat sekitar.\n\nMeningkatkan partisipasi mahasiswa dalam berbagai kegiatan kampus, baik akademik maupun non-akademik, dengan mengedepankan kolaborasi lintas fakultas.', 'Kolaborasi untuk Inovasi, Kepedulian untuk Masyarakat.', 'pic2.jpg', 'DIRGANTARA JOSHUA & REGINA YASMIN'),
(3, '\"Membangun BEM yang berorientasi pada pengembangan potensi, solidaritas, dan kemandirian mahasiswa untuk mencapai kemajuan bersama.\"', 'Menyelenggarakan pelatihan dan seminar yang fokus pada pengembangan diri dan kemandirian mahasiswa.\r\n\r\nMendorong kerjasama antarorganisasi kampus untuk memperkuat jaringan dan solidaritas mahasiswa.\r\n\r\nMenggerakkan kegiatan-kegiatan lingkungan dan sosial yang melibatkan mahasiswa sebagai wujud kepedulian terhadap isu-isu global.', 'Membangun Potensi, Menguatkan Solidaritas!', 'pic3.jpg', 'JONATHAN XALENDRA & ADINDA NAILAH');

-- --------------------------------------------------------

--
-- Table structure for table `pemilih`
--

CREATE TABLE `pemilih` (
  `username` varchar(255) NOT NULL,
  `nim` int(9) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pemilih`
--

INSERT INTO `pemilih` (`username`, `nim`, `password`) VALUES
('graciella', 825230079, '1111'),
('Leny Marshalina', 825230127, '2005'),
('Siti Regina ', 825230129, '1234'),
('Stefani Viranessa', 825230134, '9090');

-- --------------------------------------------------------

--
-- Table structure for table `tetap`
--

CREATE TABLE `tetap` (
  `annual_salary` int(11) DEFAULT NULL,
  `empid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tetap`
--

INSERT INTO `tetap` (`annual_salary`, `empid`) VALUES
(0, 4);

-- --------------------------------------------------------

--
-- Table structure for table `voting`
--

CREATE TABLE `voting` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  `nim` int(9) NOT NULL,
  `pilihan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `voting`
--

INSERT INTO `voting` (`id`, `username`, `nim`, `pilihan`) VALUES
(53, 'Leny Marshalina', 825230127, 'DIRGANTARA JOSHUA & REGINA YASMIN'),
(54, 'Stefani Viranessa', 825230134, 'JONATHAN XALENDRA & ADINDA NAILAH'),
(55, 'Siti Regina ', 825230129, 'STEFANI VIRANESA & RIVAN ANGGARA'),
(56, 'graciella', 825230079, 'STEFANI VIRANESA & RIVAN ANGGARA');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hasil_voting`
--
ALTER TABLE `hasil_voting`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `kandidat`
--
ALTER TABLE `kandidat`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pemilih`
--
ALTER TABLE `pemilih`
  ADD PRIMARY KEY (`nim`);

--
-- Indexes for table `tetap`
--
ALTER TABLE `tetap`
  ADD PRIMARY KEY (`empid`);

--
-- Indexes for table `voting`
--
ALTER TABLE `voting`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `hasil_voting`
--
ALTER TABLE `hasil_voting`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT for table `kandidat`
--
ALTER TABLE `kandidat`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `voting`
--
ALTER TABLE `voting`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
