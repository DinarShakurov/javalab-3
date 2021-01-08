db.cafe.insertOne({
    name: 'Cafe #1',
    address: {
        country: 'Russia',
        region: 'Tatarstan',
        city: 'Kazan',
        street: 'Pushkina',
        building: '1'
    },
    tableCount: 25
});


db.ingredient.insertMany([
    {name: 'potato'}, {name: 'onion'}, {name: 'egg'}, {name: 'sausage'}
]);

db.dish.insertMany([
    {
        name: 'Fried potatoes',
        ingredients: [ObjectId('5ff72f2ada25c9781f566631'), ObjectId('5ff72f2ada25c9781f566632')]
    },
    {
        name: 'Scrambled eggs with sausage',
        ingredients: [ObjectId('5ff72f2ada25c9781f566633'), ObjectId('5ff72f2ada25c9781f566634')]
    },
    {
        name: 'Bottle of water'
    }
]);

db.menu.insertOne({
    name: 'Russian menu',
    menu_composition: [ObjectId('5ff74027da25c9781f566644'), ObjectId('5ff74027da25c9781f566645'), ObjectId('5ff74027da25c9781f566646')]
});

db.cafe.updateOne(
    {
        _id: ObjectId('5ff72e2bda25c9781f56662e')
    },
    {
        $set: {
            menu: ObjectId('5ff741bbc6fab0441713333e')
        }
    }
);