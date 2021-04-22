export function validRequired(value) {
    return !!value || 'Trường thông tin này bắt buộc!';
}
export function validIdentity(value) {
    if (!value) {
        return true;
    }
    if (value.length != 9) {
        if (value.length == 12) {
            return true;
        }
        return `Chứng minh thư có phải có 9 hoặc 12 chữ số`;
    }
    if (value.length != 12) {
        if (value.length == 9) {
            return true;
        }
        return `Chứng minh thư có phải có 9 hoặc 12 chữ số`;
    }
}
export function isNumber(value) {
    return !isNaN(value) || 'Trường thông tin này bắt buộc là một dãy số!';
}
export function validLengthMax(value, maxChar = '1') {
    return (value && value.length <= maxChar) || `Trường thông tin này tối đa ${maxChar} kí tự`;
}
export function validLengthMin(value, minChar = '1') {
    return (value && value.length >= minChar) || `Trường thông tin này ít nhất ${minChar} kí tự`;
}
export function validEmail(value) {
    return /\S+@\S+\.\S+/.test(value) || 'Email không hợp lệ';
}
export function validPhone(value) {
    if (value) {
        return /(84|0[3|5|7|8|9])+([0-9]{8})\b/.test(value) || 'Số điện thoại không hợp lệ';
    }
    return true;
}
