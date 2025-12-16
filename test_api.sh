#!/bin/bash

BASE_URL="http://localhost:8080/api"

echo "1. Creating Advisor..."
response=$(curl -s -X POST "$BASE_URL/advisors" -H "Content-Type: application/json" -d '{"name": "John Doe"}')
echo "Response: $response"
advisor_id=$(echo $response | grep -o '"id":[0-9]*' | head -1 | awk -F: '{print $2}')
echo "Advisor ID: $advisor_id"

if [ -z "$advisor_id" ]; then
    echo "Failed to create advisor. Exiting."
    exit 1
fi

echo "------------------------------------------------"

echo "2. Creating Client for Advisor $advisor_id..."
response=$(curl -s -X POST "$BASE_URL/clients/advisors/$advisor_id/clients" -H "Content-Type: application/json" -d '{"name": "Alice", "surname": "Smith", "phoneNumber": "1234567890"}')
echo "Response: $response"
client_id=$(echo $response | grep -o '"id":[0-9]*' | head -1 | awk -F: '{print $2}')
echo "Client ID: $client_id"

if [ -z "$client_id" ]; then
    echo "Failed to create client. Exiting."
    exit 1
fi

echo "------------------------------------------------"

echo "3. Creating Current Account for Client $client_id..."
response=$(curl -s -X POST "$BASE_URL/current-accounts/create/$client_id")
echo "Response: $response"
account_number=$(echo $response | grep -o '"accountNumber":"[^"]*"' | head -1 | awk -F'"' '{print $4}')
echo "Account Number: $account_number"

if [ -z "$account_number" ]; then
    echo "Failed to create account. Exiting."
    # do not exit, maybe we can test other things or create another client
fi

echo "------------------------------------------------"

echo "4. Creating Second Client and Account for Transfer..."
response=$(curl -s -X POST "$BASE_URL/clients/advisors/$advisor_id/clients" -H "Content-Type: application/json" -d '{"name": "Bob", "surname": "Jones", "phoneNumber": "0987654321"}')
client2_id=$(echo $response | grep -o '"id":[0-9]*' | head -1 | awk -F: '{print $2}')
echo "Client 2 ID: $client2_id"

response=$(curl -s -X POST "$BASE_URL/current-accounts/create/$client2_id")
account2_number=$(echo $response | grep -o '"accountNumber":"[^"]*"' | head -1 | awk -F'"' '{print $4}')
echo "Account 2 Number: $account2_number"

echo "------------------------------------------------"

echo "5. Add Money to Account 1 ($account_number)..."
curl -s -X POST "$BASE_URL/accounts/$account_number/add" -H "Content-Type: application/json" -d '1000'
echo ""

echo "------------------------------------------------"

echo "6. Get Account 1 Balance..."
curl -s -X GET "$BASE_URL/accounts/$account_number"
echo ""

echo "------------------------------------------------"

echo "7. Transfer 100 from Account 1 to Account 2..."
# Transfer controller takes request params
curl -s -X POST "$BASE_URL/transfers?source=$account_number&destination=$account2_number&amount=100"
echo "Transfer Request Sent (Void response expected for success)"
echo ""

echo "------------------------------------------------"

echo "8. Check Balances..."
echo "Account 1:"
curl -s -X GET "$BASE_URL/accounts/$account_number"
echo ""
echo "Account 2:"
curl -s -X GET "$BASE_URL/accounts/$account2_number"
echo ""

echo "------------------------------------------------"

echo "9. Test Get Clients by Advisor..."
curl -s -X GET "$BASE_URL/clients/advisors/$advisor_id/clients"
echo ""

echo "Done."
