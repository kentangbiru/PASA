class PasswordService {
    constructor() {
        this.strengthLevels = {
            WEAK: 1,
            MEDIUM: 2,
            STRONG: 3
        };
        this.commonPasswords = ["password", "123456", "qwerty", "abc123", "admin", "iloveyou", "admin123"];
    }

    // Fungsi untuk mengecek keacakan string
    checkRandomness(str) {
        // Cek pola berurutan (seperti: abcdef, 123456)
        const sequential = /abcdef|123456|qwerty|asdfgh/i;
        if (sequential.test(str)) return false;

        // Cek pengulangan karakter yang sama (seperti: aaa, 111)
        const repeatedChars = /(.)\1{2,}/;
        if (repeatedChars.test(str)) return false;

        // Cek pola berulang (seperti: abcabc, 123123)
        const repeatedPattern = /^(.+?)\1+$/;
        if (repeatedPattern.test(str)) return false;

        // Cek pola alternatif (seperti: ababab, 121212)
        const alternatingPattern = /^(.{1,3})(?:\1){2,}/;
        if (alternatingPattern.test(str)) return false;

        return true;
    }

    validateInput(password) {
        if (!password || password.trim() === "") {
            throw new InputError("Password tidak boleh kosong.");
        }

        if (password.length > 20) {
            throw new InputError("Agar mudah diingat, Password tidak boleh lebih dari 20 karakter.");
        }

        if (this.commonPasswords.includes(password.toLowerCase())) {
            throw new InputError("Password termasuk dalam kategori umum. Gunakan password yang lebih unik.");
        }
    }

    getRecommendations(passwordAnalysis) {
        const recommendations = [];
        if (!passwordAnalysis.hasMinLength) recommendations.push("Tambahkan lebih banyak karakter (minimal 8).");
        if (!passwordAnalysis.hasUpperCase) recommendations.push("Gunakan minimal 1 huruf besar.");
        if (!passwordAnalysis.hasLowerCase) recommendations.push("Gunakan minimal 1 huruf kecil.");
        if (!passwordAnalysis.hasNumbers) recommendations.push("Gunakan minimal 1 angka.");
        if (!passwordAnalysis.hasSpecialChars) recommendations.push("Tambahkan minimal 1 karakter spesial (!@#$%^&*).");
        if (!passwordAnalysis.isRandom) recommendations.push("Hindari pola berulang atau berurutan.");

        return recommendations;
    }

    checkStrength(password) {
        this.validateInput(password);

        // Cek keacakan password
        const isRandom = this.checkRandomness(password);
        
        // Jika tidak acak, langsung kembalikan status weak
        if (!isRandom) {
            return {
                strengthLevel: this.strengthLevels.WEAK,
                strengthName: "weak",
                message: "Password lemah karena mengandung pola yang mudah ditebak.",
                analysis: {
                    hasMinLength: password.length >= 8,
                    hasUpperCase: /[A-Z]/.test(password),
                    hasLowerCase: /[a-z]/.test(password),
                    hasNumbers: /\d/.test(password),
                    hasSpecialChars: /[!@#$%^&*(),.?":{}|<>]/.test(password),
                    isRandom: false,
                    criteriaCount: 0
                },
                recommendations: ["Gunakan kombinasi karakter yang lebih acak dan hindari pola yang mudah ditebak."]
            };
        }

        // Kriteria dasar
        const hasMinLength = password.length >= 8;
        const hasUpperCase = /[A-Z]/.test(password);
        const hasLowerCase = /[a-z]/.test(password);
        const hasNumbers = /\d/.test(password);
        const hasSpecialChars = /[!@#$%^&*(),.?":{}|<>]/.test(password);

        // Hitung total kriteria yang terpenuhi
        const criteriaCount = [
            hasMinLength,
            hasUpperCase,
            hasLowerCase,
            hasNumbers,
            hasSpecialChars,
            isRandom
        ].filter(Boolean).length;

        // Tentukan level kekuatan password
        let strengthLevel;
        let strengthName;
        let message;

        if (criteriaCount <= 3) {
            strengthLevel = this.strengthLevels.WEAK;
            strengthName = "weak";
            message = "Password lemah.";
        } else if (criteriaCount <= 5) {
            strengthLevel = this.strengthLevels.MEDIUM;
            strengthName = "medium";
            message = "Password sedang.";
        } else {
            strengthLevel = this.strengthLevels.STRONG;
            strengthName = "strong";
            message = "Password kuat.";
        }

        const recommendations = this.getRecommendations({
            hasMinLength,
            hasUpperCase,
            hasLowerCase,
            hasNumbers,
            hasSpecialChars,
            isRandom
        });

        return {
            strengthLevel,
            strengthName,
            message,
            analysis: {
                hasMinLength,
                hasUpperCase,
                hasLowerCase,
                hasNumbers,
                hasSpecialChars,
                isRandom,
                criteriaCount
            },
            recommendations
        };
    }
}

module.exports = PasswordService;