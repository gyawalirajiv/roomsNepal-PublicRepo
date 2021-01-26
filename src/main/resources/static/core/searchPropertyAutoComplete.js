$(function () {
    var availableTags = [
        "Anamnagar",
        "Asan",
        "Babarmahal",
        "Bafal",
        "Bagbazar",
        "Bagdol",
        "Badegau",
        "Bageswori",
        "Bakhundol",
        "Balaju",
        "Balkhu",
        "Balkot",
        "Balkumari",
        "Baluwatar",
        "Banasthali",
        "Baneswor",
        "Purano Baneswor",
        "Bansbari",
        "Bhattedanda",
        "Basantpur",
        "Basundhara",
        "Battishputali",
        "Bauddha",
        "Bhaisepati",
        "Bhatbhateni",
        "Bhimsengola",
        "Bhuwanimandal",
        "Bijulibazar",
        "Bijeshwari",
        "Bishalnagar",
        "Boudha",
        "Budhanilkantha",
        "Buddhanagar",
        "Chahabil",
        "Chakupat",
        "Chandol",
        "Chapali",
        "ChapaliBhadrakali",
        "Chapagaun",
        "Chhauni",
        "Chapalkarkhana",
        "Chhauni",
        "Chhetrapati",
        "Chobhar",
        "Chuchepati",
        "Chundevi",
        "Changu Narayan",
        "Dallu",
        "Darbarmarga",
        "Dhapakhel",
        "Dhapasi",
        "Dhobighat",
        "Dholahiti",
        "Dhumbarahi",
        "Dillibazar",
        "Ekantkuna",
        "Gabahal",
        "Gahabal",
        "Gahanapokhari",
        "Gairidhara",
        "Galfutar",
        "Gangabu",
        "Gaurighat",
        "Gaushala",
        "Ghattekulo",
        "Gathaghar",
        "Godavari",
        "Goldunga",
        "Golkopakha",
        "Gothatar",
        "Gwarko",
        "Gyaneshwor",
        "Harisiddhi",
        "Hattiban",
        "Hattigaunda",
        "Hattisar",
        "Hepaligaun",
        "chungal",
        "Imadol",
        "Indra Chowk",
        "Jadibuti",
        "Jamal",
        "Jawalakhel",
        "Jhamsikhel",
        "Jhapa",
        "Jorpati",
        "Jwagal",
        "Kalanki",
        "Kalikasthan",
        "Kalimati",
        "Kalopool",
        "kamaladi",
        "Kamalpokhari",
        "Kandaghari",
        "Kapan",
        "Khumaltar",
        "Khusibu",
        "Kanibahal",
        "Kotdanda",
        "Koteshwor",
        "Katunje",
        "Kritipur",
        "Kuleswor",
        "Kumaripati",
        "Kumarigal",
        "Kupandol",
        "Kusunti",
        "Lagankhel",
        "Lokanthali",
        "Lamataar",
        "Lamatar",
        "Lazimpat",
        "Lubhu",
        "Mahalaxmisthan",
        "Mahankal",
        "Maharajganj",
        "Mahepi",
        "Maijubahal",
        "Maitidevi",
        "Maitighar",
        "Maligaun",
        "Machhegaun",
        "Manbhawan",
        "Mandikatar",
        "Mangalbazar",
        "Manamaiju",
        "Minbhawan",
        "Mitrapark",
        "Mulpani",
        "Naikab",
        "Nagarkot",
        "Nakhipot",
        "Nakhu",
        "Nakhudol",
        "Nakkhu",
        "Narayanthan",
        "Narephant",
        "Naxal",
        "Nayabazar",
        "Nepalganj",
        "Newroad",
        "New Baneshwar",
        "Paknajol",
        "Panauti",
        "Panipokhari",
        "Pharping",
        "Pasikot",
        "Pulchok",
        "Putalisadak",
        "Ravibhawan",
        "Ratopool",
        "Samakhusi",
        "Sanepa",
        "Sanogaau",
        "Santinagar",
        "Sankhamul",
        "Satdobato",
        "Seetapaila",
        "Setipakha",
        "Shankhmul",
        "Shantinagar",
        "Shifal",
        "Shiphal",
        "Sinamangal",
        "Sitapaila",
        "Soltimod",
        "Sunakothi",
        "Sukedhara",
        "Sundhara",
        "Swayambhu",
        "Swichatar",
        "Talsikhel",
        "Tangal",
        "Tahachal",
        "Thaiba",
        "Thamel",
        "Thankot",
        "Thapagaun",
        "Thapathali",
        "Thasikhel",
        "Thechu",
        "Tribhuwan Airport",
        "Tripureshwor",
        "Tikhedewal",
        "Tinkune",
        "Tokha",
        "Tripureswor",
        "Tyanglaphat",
        "Vanasthali"
    ];
    $("#tags").autocomplete({
        source: function (request, response) {
            var results = $.ui.autocomplete.filter(availableTags, request.term);
            var filterSortedArray = [];
            var sortedArray = [];
            var requestChar = request.term.charAt(0);

            for (var i = 0; i < results.length; i++) {
                var resultChar = results[i].charAt(0);
                if (resultChar.toLowerCase() == requestChar.toLowerCase()) {
                    filterSortedArray.unshift(results[i]);
                    continue;
                }
                sortedArray.push(results[i]);
            }

            for (var i = 0; i < filterSortedArray.length; i++) {
                sortedArray.unshift(filterSortedArray[i]);
            }
            response(sortedArray.slice(0, 10));
        },
    });
});
