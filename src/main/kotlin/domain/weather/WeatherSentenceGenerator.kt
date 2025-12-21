package com.despaircorp.domain.weather

object WeatherSentenceGenerator {
    fun getWeatherPhrase(condition: String, temp: Double, lang: String): String {
        val conditionPhrases = when (condition) {
            "Thunderstorm" -> if (lang == "fr") listOf(
                "Zeus est en colère aujourd'hui ⚡",
                "Thor fait du tambour là-haut ⚡",
                "Le ciel fait sa crise de nerfs ⚡",
                "Évite les arbres et les parapluies en métal ⚡",
                "Nature's rave party ⚡",
                "Le ciel joue à Pikachu ⚡",
                "Orage, ô désespoir ⚡",
                "Les dieux font leur battle de DJ ⚡",
                "Ambiance fin du monde ⚡",
                "Le ciel a besoin d'un Xanax ⚡"
            ) else listOf(
                "Zeus is angry today ⚡",
                "Thor is drumming up there ⚡",
                "The sky is having a meltdown ⚡",
                "Avoid trees and metal umbrellas ⚡",
                "Nature's rave party ⚡",
                "The sky is playing Pikachu ⚡",
                "Storm vibes, pure chaos ⚡",
                "The gods are having a DJ battle ⚡",
                "End of the world mood ⚡",
                "The sky needs a Xanax ⚡"
            )
            "Drizzle" -> if (lang == "fr") listOf(
                "Un petit crachin, parapluie optionnel 🌧️",
                "Ça bruine, ça mouille à peine 🌧️",
                "Bretagne mood 🌧️",
                "Le ciel pleure doucement 🌧️",
                "Cheveux frisés garantis 🌧️",
                "Pluie de feignant 🌧️",
                "Même pas assez pour annuler un match 🌧️",
                "Le nuage a une fuite 🌧️",
                "Micro-douche gratuite 🌧️",
                "Le ciel fait pipi 🌧️"
            ) else listOf(
                "Light drizzle, umbrella optional 🌧️",
                "Barely getting wet 🌧️",
                "London mood 🌧️",
                "The sky is crying softly 🌧️",
                "Frizzy hair guaranteed 🌧️",
                "Lazy rain 🌧️",
                "Not enough to cancel a game 🌧️",
                "The cloud has a leak 🌧️",
                "Free micro-shower 🌧️",
                "The sky is taking a leak 🌧️"
            )
            "Rain" -> if (lang == "fr") listOf(
                "Les escargots sont de sortie 🐌",
                "Parapluie obligatoire 🌧️",
                "Journée Netflix validée 🌧️",
                "Les canards sont contents 🌧️",
                "Ça tombe comme à Gravelotte 🌧️",
                "Temps de chien 🌧️",
                "Les flaques t'attendent 🌧️",
                "Prends tes bottes ou meurs 🌧️",
                "Le ciel a ouvert les vannes 🌧️",
                "Journée plaid et chocolat chaud 🌧️"
            ) else listOf(
                "Snails are out 🐌",
                "Umbrella mandatory 🌧️",
                "Netflix day approved 🌧️",
                "Ducks are happy 🌧️",
                "It's pouring cats and dogs 🌧️",
                "Crappy weather 🌧️",
                "Puddles are waiting for you 🌧️",
                "Grab your boots or die 🌧️",
                "The sky opened the floodgates 🌧️",
                "Blanket and hot cocoa day 🌧️"
            )
            "Snow" -> if (lang == "fr") listOf(
                "Bonhomme de neige en construction ☃️",
                "Il fait un froid de canard ☃️",
                "Narnia vibes ☃️",
                "La Reine des Neiges a encore frappé ☃️",
                "Bataille de boules autorisée ☃️",
                "Les skis sont de sortie ☃️",
                "Marche pas sur le verglas ☃️",
                "Chocolat chaud obligatoire ☃️",
                "Le monde devient tout blanc ☃️",
                "Game of Thrones IRL ☃️"
            ) else listOf(
                "Snowman under construction ☃️",
                "It's freezing cold ☃️",
                "Narnia vibes ☃️",
                "Elsa strikes again ☃️",
                "Snowball fight authorized ☃️",
                "Skis are out ☃️",
                "Don't slip on the ice ☃️",
                "Hot chocolate mandatory ☃️",
                "The world is turning white ☃️",
                "Game of Thrones IRL ☃️"
            )
            "Mist" -> if (lang == "fr") listOf(
                "Mode Silent Hill activé 🌫️",
                "Tu vois pas à 10 mètres 🌫️",
                "Ambiance film d'horreur 🌫️",
                "Roule doucement 🌫️",
                "Les fantômes sont de sortie 🌫️",
                "Brouillard de ouf 🌫️",
                "GPS obligatoire 🌫️",
                "Ambiance Londres 1888 🌫️",
                "Stephen King approuve 🌫️",
                "Visibilité niveau taupe 🌫️"
            ) else listOf(
                "Silent Hill mode activated 🌫️",
                "Can't see 10 meters ahead 🌫️",
                "Horror movie vibes 🌫️",
                "Drive slowly 🌫️",
                "Ghosts are out 🌫️",
                "Crazy fog 🌫️",
                "GPS mandatory 🌫️",
                "London 1888 vibes 🌫️",
                "Stephen King approves 🌫️",
                "Visibility level: mole 🌫️"
            )
            "Smoke" -> if (lang == "fr") listOf(
                "L'air est pas frais aujourd'hui 💨",
                "Ferme tes fenêtres 💨",
                "Masque recommandé 💨",
                "Quelqu'un a cramé un truc 💨",
                "Poumons en PLS 💨",
                "BBQ géant dans le coin 💨",
                "L'air a un goût bizarre 💨",
                "Évite de courir dehors 💨",
                "Mode pollution activé 💨",
                "Respire avec modération 💨"
            ) else listOf(
                "Air isn't fresh today 💨",
                "Close your windows 💨",
                "Mask recommended 💨",
                "Someone burned something 💨",
                "Lungs in panic mode 💨",
                "Giant BBQ nearby 💨",
                "Air tastes weird 💨",
                "Don't run outside 💨",
                "Pollution mode activated 💨",
                "Breathe with moderation 💨"
            )
            "Haze" -> if (lang == "fr") listOf(
                "Brume légère, ambiance mystique 🌁",
                "Le soleil galère à percer 🌁",
                "Ciel voilé 🌁",
                "Ambiance rêve éveillé 🌁",
                "Tout est un peu flou 🌁",
                "Le ciel fait la sieste 🌁",
                "Lumière diffuse, photos stylées 🌁",
                "Ambiance aquarelle 🌁",
                "Le soleil joue à cache-cache 🌁",
                "Douceur brumeuse 🌁"
            ) else listOf(
                "Light haze, mystic vibes 🌁",
                "Sun is struggling to break through 🌁",
                "Veiled sky 🌁",
                "Daydream vibes 🌁",
                "Everything is a bit blurry 🌁",
                "The sky is napping 🌁",
                "Diffused light, stylish photos 🌁",
                "Watercolor vibes 🌁",
                "Sun is playing hide and seek 🌁",
                "Hazy softness 🌁"
            )
            "Dust" -> if (lang == "fr") listOf(
                "Mange pas trop de poussière 🏜️",
                "Mad Max vibes 🏜️",
                "Lunettes de protection conseillées 🏜️",
                "Le désert s'invite 🏜️",
                "Ta voiture va déguster 🏜️",
                "Ferme les fenêtres vite 🏜️",
                "Sahara en approche 🏜️",
                "Ça pique les yeux 🏜️",
                "Ambiance western 🏜️",
                "Le vent ramène du sable 🏜️"
            ) else listOf(
                "Don't eat too much dust 🏜️",
                "Mad Max vibes 🏜️",
                "Protective glasses recommended 🏜️",
                "The desert is visiting 🏜️",
                "Your car will suffer 🏜️",
                "Close the windows fast 🏜️",
                "Sahara incoming 🏜️",
                "Eyes are stinging 🏜️",
                "Western vibes 🏜️",
                "Wind is bringing sand 🏜️"
            )
            "Fog" -> if (lang == "fr") listOf(
                "Même les GPS sont perdus 🌫️",
                "Visibilité zéro 🌫️",
                "Conduis comme une mamie 🌫️",
                "Jack l'Éventreur mood 🌫️",
                "Tu vois même pas tes pieds 🌫️",
                "Klaxonne dans les virages 🌫️",
                "Les phares servent à rien 🌫️",
                "Ambiance purée de pois 🌫️",
                "Perdu dans le brouillard 🌫️",
                "Le monde a disparu 🌫️"
            ) else listOf(
                "Even GPS is lost 🌫️",
                "Zero visibility 🌫️",
                "Drive like a grandma 🌫️",
                "Jack the Ripper mood 🌫️",
                "Can't even see your feet 🌫️",
                "Honk in the curves 🌫️",
                "Headlights are useless 🌫️",
                "Pea soup vibes 🌫️",
                "Lost in the fog 🌫️",
                "The world disappeared 🌫️"
            )
            "Sand" -> if (lang == "fr") listOf(
                "Tempête de sable, Lawrence d'Arabie vibes 🏜️",
                "Dune en vrai 🏜️",
                "Le Sahara dit bonjour 🏜️",
                "Ferme ta bouche en marchant 🏜️",
                "Tes yeux vont morfler 🏜️",
                "Anakin déteste ça 🏜️",
                "Le sable est partout 🏜️",
                "Exfoliation gratuite 🏜️",
                "Beach vibes mais violent 🏜️",
                "Le désert attaque 🏜️"
            ) else listOf(
                "Sandstorm, Lawrence of Arabia vibes 🏜️",
                "Dune IRL 🏜️",
                "Sahara says hi 🏜️",
                "Close your mouth while walking 🏜️",
                "Your eyes will suffer 🏜️",
                "Anakin hates this 🏜️",
                "Sand is everywhere 🏜️",
                "Free exfoliation 🏜️",
                "Beach vibes but violent 🏜️",
                "The desert attacks 🏜️"
            )
            "Ash" -> if (lang == "fr") listOf(
                "Un volcan a pété quelque part 🌋",
                "Pompéi vibes 🌋",
                "Reste à l'intérieur 🌋",
                "L'air est toxique 🌋",
                "Le ciel est gris cendre 🌋",
                "Apocalypse mood 🌋",
                "Lave tes poumons après 🌋",
                "La nature est en colère 🌋",
                "Masque FFP2 obligatoire 🌋",
                "Ambiance post-apocalyptique 🌋"
            ) else listOf(
                "A volcano blew somewhere 🌋",
                "Pompeii vibes 🌋",
                "Stay inside 🌋",
                "Air is toxic 🌋",
                "Sky is ash grey 🌋",
                "Apocalypse mood 🌋",
                "Wash your lungs after 🌋",
                "Nature is angry 🌋",
                "FFP2 mask mandatory 🌋",
                "Post-apocalyptic vibes 🌋"
            )
            "Squall" -> if (lang == "fr") listOf(
                "Accroche-toi aux lampadaires 💨",
                "Le vent est fou 💨",
                "Tes cheveux vont souffrir 💨",
                "Mary Poppins incoming 💨",
                "Rafales de malade 💨",
                "Tiens ton parapluie ou perds-le 💨",
                "Le vent te pousse 💨",
                "Attention aux tuiles 💨",
                "Éole fait sa crise 💨",
                "Ça souffle grave 💨"
            ) else listOf(
                "Hold on to the lampposts 💨",
                "Wind is crazy 💨",
                "Your hair will suffer 💨",
                "Mary Poppins incoming 💨",
                "Crazy gusts 💨",
                "Hold your umbrella or lose it 💨",
                "Wind is pushing you 💨",
                "Watch out for flying tiles 💨",
                "Aeolus is having a fit 💨",
                "It's blowing hard 💨"
            )
            "Tornado" -> if (lang == "fr") listOf(
                "Reste chez toi, sérieux 🌪️",
                "Dorothy est dans le coin 🌪️",
                "Le Magicien d'Oz IRL 🌪️",
                "Cave ou abri, maintenant 🌪️",
                "Twister, mais c'est pas un film 🌪️",
                "Code rouge 🌪️",
                "Ça tourne mal 🌪️",
                "La nature est en furie 🌪️",
                "Accroche-toi à quelque chose de fixe 🌪️",
                "Pas le moment de sortir 🌪️"
            ) else listOf(
                "Stay home, seriously 🌪️",
                "Dorothy is around 🌪️",
                "Wizard of Oz IRL 🌪️",
                "Basement or shelter, now 🌪️",
                "Twister, but it's not a movie 🌪️",
                "Code red 🌪️",
                "Things are going south 🌪️",
                "Nature is furious 🌪️",
                "Hold on to something fixed 🌪️",
                "Not the time to go out 🌪️"
            )
            "Clear" -> if (lang == "fr") listOf(
                "Grand bleu, lunettes de soleil obligatoires 😎",
                "Ciel parfait ☀️",
                "Journée terrasse ☀️",
                "Le soleil tape ☀️",
                "Crème solaire sinon coup de soleil ☀️",
                "Beach day possible ☀️",
                "Pas un nuage à l'horizon ☀️",
                "Vitamine D en intraveineuse ☀️",
                "Le ciel est au max ☀️",
                "Sortez les BBQ ☀️"
            ) else listOf(
                "Clear skies, sunglasses mandatory 😎",
                "Perfect sky ☀️",
                "Terrace day ☀️",
                "Sun is hitting hard ☀️",
                "Sunscreen or sunburn ☀️",
                "Beach day possible ☀️",
                "Not a cloud in sight ☀️",
                "Vitamin D overload ☀️",
                "Sky is maxed out ☀️",
                "BBQ time ☀️"
            )
            "Clouds" -> if (lang == "fr") listOf(
                "Ciel gris, moral en option ☁️",
                "Les nuages font la teuf ☁️",
                "Soleil en grève ☁️",
                "Grisaille assumée ☁️",
                "Ambiance déprime automnale ☁️",
                "Le ciel boude ☁️",
                "Météo meh ☁️",
                "Ni pluie ni soleil, le néant ☁️",
                "Le ciel a pas choisi ☁️",
                "Journée Netflix par défaut ☁️"
            ) else listOf(
                "Grey sky, mood optional ☁️",
                "Clouds are partying ☁️",
                "Sun is on strike ☁️",
                "Embracing the grey ☁️",
                "Autumn depression vibes ☁️",
                "The sky is sulking ☁️",
                "Meh weather ☁️",
                "No rain no sun, just void ☁️",
                "The sky can't decide ☁️",
                "Default Netflix day ☁️"
            )
            else -> if (lang == "fr") listOf("Météo inconnue 🤷") else listOf("Unknown weather 🤷")
        }

        val tempPhrases = when {
            temp < 0 -> if (lang == "fr") listOf(
                "Il fait un froid de canard 🥶",
                "Tes doigts vont tomber 🥶",
                "Même les pingouins se plaignent 🥶",
                "Sibérie mode 🥶",
                "Le froid te transperce 🥶",
                "Ton haleine fait de la fumée 🥶",
                "Les oreilles vont geler 🥶",
                "Reste sous la couette 🥶",
                "Hiver nucléaire 🥶",
                "Le chauffage est ton meilleur ami 🥶"
            ) else listOf(
                "It's freezing cold 🥶",
                "Your fingers will fall off 🥶",
                "Even penguins are complaining 🥶",
                "Siberia mode 🥶",
                "Cold is piercing through you 🥶",
                "Your breath is smoking 🥶",
                "Ears will freeze 🥶",
                "Stay under the blanket 🥶",
                "Nuclear winter 🥶",
                "Heater is your best friend 🥶"
            )
            temp < 10 -> if (lang == "fr") listOf(
                "Sors pas sans ta doudoune 🧥",
                "Ça caille sévère 🧥",
                "Bonnet obligatoire 🧥",
                "Le froid pique 🧥",
                "Mains dans les poches 🧥",
                "Écharpe validée 🧥",
                "Thé chaud recommandé 🧥",
                "Automne bien installé 🧥",
                "Frissons garantis 🧥",
                "Le chauffage tourne 🧥"
            ) else listOf(
                "Don't go out without your jacket 🧥",
                "It's damn cold 🧥",
                "Beanie mandatory 🧥",
                "Cold is biting 🧥",
                "Hands in pockets 🧥",
                "Scarf approved 🧥",
                "Hot tea recommended 🧥",
                "Autumn is settled 🧥",
                "Shivers guaranteed 🧥",
                "Heating is on 🧥"
            )
            temp < 15 -> if (lang == "fr") listOf(
                "Petite veste conseillée 🧣",
                "Frais mais supportable 🧣",
                "Mi-saison mood 🧣",
                "Un petit pull suffit 🧣",
                "Ni chaud ni froid 🧣",
                "Le corps hésite 🧣",
                "Température de Breton 🧣",
                "Confort relatif 🧣",
                "Une couche suffit 🧣",
                "Agréable à l'ombre, frais au soleil 🧣"
            ) else listOf(
                "Light jacket recommended 🧣",
                "Cool but bearable 🧣",
                "Mid-season mood 🧣",
                "A light sweater is enough 🧣",
                "Neither hot nor cold 🧣",
                "Body is confused 🧣",
                "British temperature 🧣",
                "Relative comfort 🧣",
                "One layer is enough 🧣",
                "Nice in the shade, cool in the sun 🧣"
            )
            temp < 20 -> if (lang == "fr") listOf(
                "Température parfaite, profite 👌",
                "Le sweet spot météo 👌",
                "Ni trop ni pas assez 👌",
                "Journée idéale 👌",
                "La météo est avec toi 👌",
                "Conditions optimales 👌",
                "Goldilocks approved 👌",
                "Parfait pour tout 👌",
                "Le bonheur climatique 👌",
                "Météo de rêve 👌"
            ) else listOf(
                "Perfect temperature, enjoy 👌",
                "Weather sweet spot 👌",
                "Not too much not too little 👌",
                "Ideal day 👌",
                "Weather is on your side 👌",
                "Optimal conditions 👌",
                "Goldilocks approved 👌",
                "Perfect for everything 👌",
                "Climate happiness 👌",
                "Dream weather 👌"
            )
            temp < 25 -> if (lang == "fr") listOf(
                "T-shirt weather ☀️",
                "L'été est là ☀️",
                "Terrasse obligatoire ☀️",
                "Glace autorisée ☀️",
                "Bras nus validés ☀️",
                "Short weather incoming ☀️",
                "Bronzage possible ☀️",
                "Journée piscine ☀️",
                "Apéro en terrasse ☀️",
                "La chaleur arrive ☀️"
            ) else listOf(
                "T-shirt weather ☀️",
                "Summer is here ☀️",
                "Terrace mandatory ☀️",
                "Ice cream allowed ☀️",
                "Bare arms approved ☀️",
                "Shorts weather incoming ☀️",
                "Tanning possible ☀️",
                "Pool day ☀️",
                "Drinks on the terrace ☀️",
                "Heat is coming ☀️"
            )
            temp < 30 -> if (lang == "fr") listOf(
                "Glace ou clim, faut choisir 🍦",
                "Ça commence à taper 🍦",
                "Hydrate-toi 🍦",
                "La sueur arrive 🍦",
                "Ventilo en PLS 🍦",
                "Sieste à l'ombre 🍦",
                "Piscine nécessaire 🍦",
                "Le bitume fond presque 🍦",
                "Évite le sport à midi 🍦",
                "Chaleur bien présente 🍦"
            ) else listOf(
                "Ice cream or AC, pick one 🍦",
                "Starting to hit hard 🍦",
                "Stay hydrated 🍦",
                "Sweat is coming 🍦",
                "Fan is dying 🍦",
                "Nap in the shade 🍦",
                "Pool needed 🍦",
                "Asphalt is almost melting 🍦",
                "Avoid sports at noon 🍦",
                "Heat is here 🍦"
            )
            else -> if (lang == "fr") listOf(
                "On cuit comme des merguez 🥵",
                "Canicule activée 🥵",
                "Reste à l'ombre 🥵",
                "L'enfer sur Terre 🥵",
                "Clim ou mort 🥵",
                "Même les lézards galèrent 🥵",
                "Alerte rouge chaleur 🥵",
                "Bois 3 litres minimum 🥵",
                "Le soleil veut ta peau 🥵",
                "Survie mode activé 🥵"
            ) else listOf(
                "Cooking like sausages 🥵",
                "Heatwave activated 🥵",
                "Stay in the shade 🥵",
                "Hell on Earth 🥵",
                "AC or death 🥵",
                "Even lizards are struggling 🥵",
                "Red alert heat 🥵",
                "Drink 3 liters minimum 🥵",
                "The sun wants your skin 🥵",
                "Survival mode activated 🥵"
            )
        }

        return "${conditionPhrases.random()}\n${tempPhrases.random()}"
    }
}