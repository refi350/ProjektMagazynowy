// Please see documentation at https://docs.microsoft.com/aspnet/core/client-side/bundling-and-minification
// for details on configuring this project to bundle and minify static web assets.

// Write your JavaScript code.
function lightenHexColor(hex, percent) {
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

function adjustTextColorForContrast(elementId) {
    const element = document.getElementById(elementId);
    const backgroundColor = getComputedStyle(element).backgroundColor;
    const color = getComputedStyle(element).color;

    const getContrast = (bgColor, textColor) => {
        const bg = getRGBArray(bgColor);
        const text = getRGBArray(textColor);

        const bgLuminance = getLuminance(bg[0], bg[1], bg[2]);
        const textLuminance = getLuminance(text[0], text[1], text[2]);

        const contrast = (bgLuminance + 0.05) / (textLuminance + 0.05);

        return contrast;
    };

    const getRGBArray = (color) => {
        const match = color.match(/rgb\((\d+), (\d+), (\d+)\)/);
        if (match) {
            return [parseInt(match[1]), parseInt(match[2]), parseInt(match[3])];
        }
        return [];
    };

    const getLuminance = (r, g, b) => {
        const a = [r, g, b].map((v) => {
            v /= 255;
            return v <= 0.03928 ? v / 12.92 : Math.pow((v + 0.055) / 1.055, 2.4);
        });
        return 0.2126 * a[0] + 0.7152 * a[1] + 0.0722 * a[2];
    };

    const contrastRatio = getContrast(backgroundColor, color);

    if (contrastRatio < 4.5) {
        element.style.color = '#fff'; // Kolor czcionki zostaje zmieniony na biały
    } else {
        element.style.color = "#000";
    }
}
