const request = require('supertest');
const app = require('./server');

describe('Bad IP Endpoint', () => {
    
    // Test 1: Check a known Bad IP
    it('should label 100.200.300.400 as Bad IP', async () => {
        const res = await request(app).get('/check-bad?ip=100.200.300.400');
        expect(res.statusCode).toEqual(200);
        expect(res.body.result).toEqual('Bad IP');
    });

    // Test 2: Check a known Good IP
    it('should label 1.2.3.4 as Good IP', async () => {
        const res = await request(app).get('/check-bad?ip=1.2.3.4');
        expect(res.statusCode).toEqual(200);
        expect(res.body.result).toEqual('Good IP');
    });
});
