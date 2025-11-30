const weatherForm = document.querySelector('#weather-search');
    
    if(weatherForm) {
        weatherForm.addEventListener('submit', async (event) => {
            event.preventDefault();
            
            const inputCity = document.querySelector('#weather-city-name');
            const cityName = inputCity.value;
            const alertBox = document.querySelector('#weather-alert');
            const weatherContainer = document.querySelector("#weather-result");

            if (!cityName) {
                alertBox.innerHTML = 'Por favor, digite o nome de uma cidade.';
                alertBox.classList.remove('d-none'); 
                weatherContainer.classList.add('d-none'); 
                return;
            }

            const apiKey = '8a60b2de14f7a17c7a11706b2cfcd87c'; 
            const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${encodeURI(cityName)}&appid=${apiKey}&units=metric&lang=pt_br`;

            try {
                const btnSubmit = weatherForm.querySelector('button');
                const originalBtnContent = btnSubmit.innerHTML;
                btnSubmit.innerHTML = '<div class="spinner-border spinner-border-sm" role="status"></div>';
                btnSubmit.disabled = true;

                const results = await fetch(apiUrl);
                const json = await results.json();

                btnSubmit.innerHTML = originalBtnContent;
                btnSubmit.disabled = false;

                if (json.cod === 200) {
                    alertBox.classList.add('d-none'); 
                    weatherContainer.classList.remove('d-none'); 

                    document.querySelector('#weather-title').innerText = `${json.name}, ${json.sys.country}`;
                    
                    document.querySelector('#weather-temp-value').innerText = json.main.temp.toFixed(0); 
                    
                    document.querySelector('#weather-description').innerText = json.weather[0].description;
                    document.querySelector('#weather-img').setAttribute('src', `https://openweathermap.org/img/wn/${json.weather[0].icon}@4x.png`);
                    
                    document.querySelector('#weather-max').innerHTML = `${json.main.temp_max.toFixed(1)}°C`;
                    document.querySelector('#weather-min').innerHTML = `${json.main.temp_min.toFixed(1)}°C`;
                    document.querySelector('#weather-humidity').innerHTML = `${json.main.humidity}%`;
                    document.querySelector('#weather-wind').innerHTML = `${(json.wind.speed * 3.6).toFixed(1)} km/h`; 
                
                } else {
                    weatherContainer.classList.add('d-none'); 
                    alertBox.innerHTML = `<i class="bi bi-exclamation-triangle-fill me-2"></i> Não conseguimos encontrar "${cityName}".`;
                    alertBox.classList.remove('d-none'); 
                }
            } catch (error) {
                console.error(error);
                alertBox.innerHTML = 'Erro de conexão. Verifique sua internet.';
                alertBox.classList.remove('d-none');
                weatherContainer.classList.add('d-none');
                weatherForm.querySelector('button').disabled = false;
                weatherForm.querySelector('button').innerHTML = originalBtnContent || '<i class="bi bi-search"></i>';
            }
        });
    }
