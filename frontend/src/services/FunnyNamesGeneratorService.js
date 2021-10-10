export default class FunnyNamesGeneratorService {
    static generateRandomName() {
        const getRandomNumberInRange = (min, max) => {
            return Math.floor(Math.random() * (max - min) + min);
        }

        const funnyNames = ['Tasty', 'Mr. Nice Guy', 'Drops',
            'Mad', 'Proud', 'Operator', 'Sandbox',
            'Romantic', 'Sandwich', 'Hunter', 'Troll',
            'Sweet', 'Tea', 'Dramaticus', 'Purge'];

        return funnyNames[getRandomNumberInRange(0, funnyNames.length - 1)]
    }
};