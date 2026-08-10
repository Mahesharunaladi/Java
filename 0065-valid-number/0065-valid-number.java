class Solution {
    public boolean isNumber(String s) {
        return s.trim().matches("^[\\+\\-]?((\\d+\\.\\d*)|(\\.\\d+)|(\\d+))([eE][\\+\\-]?\\d+)?$");
        
    }
}