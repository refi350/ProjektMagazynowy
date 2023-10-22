// Please see documentation at https://docs.microsoft.com/aspnet/core/client-side/bundling-and-minification
// for details on configuring this project to bundle and minify static web assets.

// Write your JavaScript code.
function lightenHexColorRGB(hex, percent) {
    // Upewnij się, że wartość procentowa mieści się w zakresie 0-100.
    if (percent < 0) percent = 0;
    if (percent > 100) percent = 100;

    // Konwersja hex na RGB.
    hex = hex.replace(/^#/, '');
    const r = parseInt(hex.slice(0, 2), 16);
    const g = parseInt(hex.slice(2, 4), 16);
    const b = parseInt(hex.slice(4, 6), 16);

    // Obliczenie nowych wartości RGB.
    const delta = Math.floor((255 - r) * (percent / 100));
    const newR = r + delta;
    const newG = g + delta;
    const newB = b + delta;

    // Upewnij się, że nowe wartości RGB mieszczą się w zakresie 0-255.
    const finalR = Math.min(255, newR);
    const finalG = Math.min(255, newG);
    const finalB = Math.min(255, newB);

    // Konwersja z powrotem na format hex.
    const finalHex = `#${(finalR).toString(16)}${(finalG).toString(16)}${(finalB).toString(16)}`;

    return finalHex;
}

function lightenHexColor(hex, percent) {
    // Upewnij się, że wartość procentowa mieści się w zakresie 0-100.
    if (percent < 0) {
        percent = 0;
    }
    if (percent > 100) {
        percent = 100;
    }

    // Konwersja hex na RGB.
    hex = hex.replace(/^#/, '');
    const red = parseInt(hex.slice(0, 2), 16) / 255;
    const green = parseInt(hex.slice(2, 4), 16) / 255;
    const blue = parseInt(hex.slice(4, 6), 16) / 255;

    // Konwersja RGB na HSV.
    var { value, saturation, hue } = rgbToHsv(red, green, blue);
    console.log(value + " " + saturation + " " + hue);

    // Obliczenie nowej wartości V (jasności).
    value = Math.min(1, value + (1 - value) * (percent / 100));
    console.log(value);
    // Konwersja HSV na RGB.
    const { newRed, newGreen, newBlue } = hsvToRgb(value, saturation, hue);
    console.log(newRed + " " + newGreen + " " + newBlue);
    // Konwersja z powrotem na format hex.
    const finalHex = `#${(Number(newRed).toString(16))}${(Number(newGreen).toString(16))}${(Number(newBlue).toString(16))}`;
    return finalHex;
}

/**
 * Konwertuje kolor z przestrzeni RGB na HSV.
 * @param {number} red - Składowa czerwona (0-255).
 * @param {number} green - Składowa zielona (0-255).
 * @param {number} blue - Składowa niebieska (0-255).
 * @returns {Object} - Obiekt zawierający wartości H (odcień), S (nasycenie) i V (jasność).
 */
function rgbToHsv(red, green, blue) {
    // Przeskalowanie wartości kolorów do zakresu od 0 do 1
    const r = red / 255;
    const g = green / 255;
    const b = blue / 255;

    // Obliczenie wartości V (jasności)
    const maxValue = Math.max(r, g, b);
    const minValue = Math.min(r, g, b);
    const delta = maxValue - minValue;

    let hue, saturation, value;

    // Obliczenie odcienia (H)
    if (delta === 0) {
        hue = 0;
    } else if (maxValue === r) {
        hue = ((g - b) / delta) % 6;
    } else if (maxValue === g) {
        hue = (b - r) / delta + 2;
    } else {
        hue = (r - g) / delta + 4;
    }

    hue = Math.round((hue * 60 + 360) % 360);

    // Obliczenie nasycenia (S)
    saturation = maxValue === 0 ? 0 : Math.round((delta / maxValue) * 100);

    // Obliczenie jasności (V)
    value = Math.round(maxValue * 100);

    return { hue, saturation, value };
}
/**
 * Konwertuje kolor z przestrzeni HSV na RGB.
 * @param {number} hue - Odcień (0-360).
 * @param {number} saturation - Nasycenie (0-100).
 * @param {number} value - Jasność (0-100).
 * @returns {Object} - Obiekt zawierający wartości R (czerwony), G (zielony) i B (niebieski).
 */
function hsvToRgb(hue, saturation, value) {
    const chroma = (value / 100) * (saturation / 100);
    const x = chroma * (1 - Math.abs((hue / 60) % 2 - 1));
    const m = (value / 100) - chroma;
    const primeRgb = { red: 0, green: 0, blue: 0 };

    // Obliczenie wartości RGB w zależności od odcienia (H)
    if (0 <= hue && hue < 60) {
        primeRgb.red = chroma;
        primeRgb.green = x;
    } else if (60 <= hue && hue < 120) {
        primeRgb.red = x;
        primeRgb.green = chroma;
    } else if (120 <= hue && hue < 180) {
        primeRgb.green = chroma;
        primeRgb.blue = x;
    } else if (180 <= hue && hue < 240) {
        primeRgb.green = x;
        primeRgb.blue = chroma;
    } else if (240 <= hue && hue < 300) {
        primeRgb.red = x;
        primeRgb.blue = chroma;
    } else {
        primeRgb.red = chroma;
        primeRgb.blue = x;
    }

    // Obliczenie wartości RGB w skali 0-255
    return {
        red: Math.round((primeRgb.red + m) * 255),
        green: Math.round((primeRgb.green + m) * 255),
        blue: Math.round((primeRgb.blue + m) * 255)
    };
}


function darkenHexColor(hex, percent) {
    // Upewnij się, że wartość procentowa mieści się w zakresie 0-100.
    if (percent < 0) percent = 0;
    if (percent > 100) percent = 100;

    // Konwersja hex na RGB.
    hex = hex.replace(/^#/, '');
    const r = parseInt(hex.slice(0, 2), 16);
    const g = parseInt(hex.slice(2, 4), 16);
    const b = parseInt(hex.slice(4, 6), 16);

    // Obliczenie nowych wartości RGB.
    const delta = Math.floor((255 - r) * (percent / 100));
    const newR = r - delta;
    const newG = g - delta;
    const newB = b - delta;

    // Upewnij się, że nowe wartości RGB mieszczą się w zakresie 0-255.
    const finalR = Math.max(0, newR);
    const finalG = Math.max(0, newG);
    const finalB = Math.max(0, newB);

    // Konwersja z powrotem na format hex.
    const finalHex = `#${(finalR).toString(16)}${(finalG).toString(16)}${(finalB).toString(16)}`;

    return finalHex;
}

//function adjustTextColorForContrast(elementId) {
//    const element = document.getElementById(elementId);
//    const backgroundColor = getComputedStyle(element).backgroundColor;
//    const color = getComputedStyle(element).color;

//    const getContrast = (bgColor, textColor) => {
//        const bg = getRGBArray(bgColor);
//        const text = getRGBArray(textColor);

//        const bgLuminance = getLuminance(bg[0], bg[1], bg[2]);
//        const textLuminance = getLuminance(text[0], text[1], text[2]);

//        const contrast = (bgLuminance + 0.05) / (textLuminance + 0.05);

//        return contrast;
//    };

//    const getRGBArray = (color) => {
//        const match = color.match(/rgb\((\d+), (\d+), (\d+)\)/);
//        if (match) {
//            return [parseInt(match[1]), parseInt(match[2]), parseInt(match[3])];
//        }
//        return [];
//    };

//    const getLuminance = (r, g, b) => {
//        const a = [r, g, b].map((v) => {
//            v /= 255;
//            return v <= 0.03928 ? v / 12.92 : Math.pow((v + 0.055) / 1.055, 2.4);
//        });
//        return 0.2126 * a[0] + 0.7152 * a[1] + 0.0722 * a[2];
//    };

//    const contrastRatio = getContrast(backgroundColor, color);

//    if (contrastRatio < 4.5) {
//        element.style.color = '#fff'; // Kolor czcionki zostaje zmieniony na biały
//    } else {
//        element.style.color = "#000";
//    }
//}
