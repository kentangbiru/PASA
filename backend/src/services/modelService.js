/** @format */

// Mengimpor dependensi TensorFlow.js
const tf = require("@tensorflow/tfjs-node");
const fs = require('fs');
const path = require('path');

// Fungsi untuk memuat model dan tokenizer
async function loadModels() {
  // Menentukan URL model utama dan tokenizer berdasarkan lingkungan aplikasi
  const modelPath = process.env.APP_ENV === "local" ? process.env.LOCAL_MODEL_URL : process.env.MODEL_URL;
  const tokenizerPath = process.env.APP_ENV === "local" ? process.env.LOCAL_TOKENIZER_URL : process.env.TOKENIZER_URL;

  console.log(`Trying to load model from: ${modelPath}`);
  console.log(`Trying to load tokenizer from: ${tokenizerPath}`);

  try {
    // Memuat model utama menggunakan TensorFlow.js
    const model = await tf.loadGraphModel(modelPath);

    // Memuat tokenizer dari path lokal (gunakan fs untuk file lokal atau URL untuk file remote)
    let tokenizer = null;
    if (tokenizerPath.startsWith('http')) {
      // Jika URL, Anda bisa mengunduhnya atau menggunakan URL langsung
      console.log("Tokenizer is from URL, consider downloading or handling in your code.");
    } else {
      // Jika lokal, Anda bisa memuat file menggunakan fs
      tokenizer = fs.readFileSync(tokenizerPath, 'utf8'); // Tokenizer file (.pkl) biasanya berupa serialized data
    }

    return { model, tokenizer };
  } catch (error) {
    console.error("Error loading model or tokenizer:", error);
    throw error;
  }
}

// Mengeksport fungsi loadModels agar bisa digunakan di file lain
module.exports = loadModels;
