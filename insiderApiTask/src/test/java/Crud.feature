Feature: Crud

  Background:
    * url baseUrl
    * header Accept = 'application/json'
    * def requestParams = read('crud.json')
    * configure driver = { type: '#(driverType)', executable: '#(driverExecutable)', addOptions: #(driverOptions), headless: #(driverHeadless), webDriverSession: #(webDriverSession), showDriverLog: true }


  Scenario: Uploads an image
    #* form field file = '@Chow chow image.jpg;type=image/jpeg'
    Given path 'pet/#/'
    And request karate.read("file:src/test/java/Chowchowimage.jpg")
    When method post
    Then status 200
    And print response


  Scenario: Add a new pet to the store
    Given path '/v2/pet'
    And request requestParams.addRequest
    When method post
    Then status 200
    And print response


  Scenario: Update an existing pet
    Given path '/v2/pet'
    And request requestParams.updateRequest
    When method post
    Then status 200
    And print response


  Scenario: Finds pets by Sold status
    Given path 'v2/pet/findByStatus'
    And params requestParams.findByStatusSoldRequest
    When method get
    Then status 200
    And print response
    * match response[*].status contains 'sold'


  Scenario: Finds pets by Available status
    Given path 'v2/pet/findByStatus'
    And params requestParams.findByStatusAvailableRequest
    When method get
    Then status 200
    And print response
    * match response[*].status contains 'available'


  Scenario: Finds pets by Pending Status
    Given path 'v2/pet/findByStatus'
    And params requestParams.findByStatusPendingRequest
    When method get
    Then status 200
    And print response
    * match response[*].status contains 'pending'


  Scenario: Finds pets by 10 Id
    Given path 'v2/pet/10'
    When method get
    Then status 200
    And print response

  Scenario: Finds pets by Id Null
    Given path 'v2/pet/'
    When method get
    Then status 405
    And print response

  Scenario: Updates a pet in the store with form data
    Given path '/v2/pet'
    And request requestParams.updateFormDataRequest
    When method post
    Then status 200
    And print response

  Scenario: Deletes a pet
    Given path '/v2/pet'
    And request  requestParams.deleteRequest
    When method delete
    Then status 405
    And print response


