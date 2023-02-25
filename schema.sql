CREATE TABLE player_entity (
    id_player UUID NOT NULL,
    pseudo VARCHAR(255) NOT NULL,
    tokens INT NOT NULL,
    PRIMARY KEY (id_player)
);
CREATE TABLE cards_pack (
    id_cards_pack UUID NOT NULL,
    type VARCHAR(255) NOT NULL,
    required_tokens INT NOT NULL,
    cards_number INT NOT NULL,
    legendary_drop_rate FLOAT NOT NULL,
    rare_drop_rate FLOAT NOT NULL,
    common_drop_rate FLOAT NOT NULL,
    PRIMARY KEY (id_cards_pack)
);
CREATE TABLE hero_entity (
    id_hero UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    speciality VARCHAR(255) NOT NULL,
    rarity VARCHAR(255) NOT NULL,
    hp INT NOT NULL,
    xp INT NOT NULL,
    power INT NOT NULL,
    armor INT NOT NULL,
    level INT NOT NULL,
    PRIMARY KEY (id_hero)
);
CREATE TABLE deck_entity (
    id_deck UUID NOT NULL,
    id_player UUID NOT NULL,
    PRIMARY KEY (id_deck)
);
INSERT INTO player_entity (id_player,PSEUDO,TOKENS) VALUES ('56c7c1e9-7d60-11ed-8473-fa163ee9d956','Pseudo1',4);
INSERT INTO cards_pack (id_cards_pack,TYPE,REQUIRED_TOKENS,CARDS_NUMBER,LEGENDARY_DROP_RATE,RARE_DROP_RATE,COMMON_DROP_RATE) VALUES ('56d03efa-7d60-11ed-8473-fa163ee9d956','DIAMOND',2,5,0.15,0.35,0.5);
INSERT INTO cards_pack (id_cards_pack,TYPE,REQUIRED_TOKENS,CARDS_NUMBER,LEGENDARY_DROP_RATE,RARE_DROP_RATE,COMMON_DROP_RATE) VALUES ('56d03efa-7d60-11ed-8473-fa163ee9d957','SILVER',1,3,0.05,0.2,0.75);
INSERT INTO hero_entity (id_hero,NAME,SPECIALITY,RARITY,HP,XP,POWER,ARMOR,LEVEL) VALUES (UUID(),'Hero1','TANK','LEGENDARY',1200,0,120,24,1);
INSERT INTO hero_entity (id_hero,NAME,SPECIALITY,RARITY,HP,XP,POWER,ARMOR,LEVEL) VALUES (UUID(),'Hero2','TANK','RARE',1100,0,110,22,1);
INSERT INTO hero_entity (id_hero,NAME,SPECIALITY,RARITY,HP,XP,POWER,ARMOR,LEVEL) VALUES (UUID(),'Hero3','TANK','COMMON',1000,0,100,20,1);
INSERT INTO deck_entity (id_deck,ID_PLAYER) VALUES (UUID(),'56c7c1e9-7d60-11ed-8473-fa163ee9d956');