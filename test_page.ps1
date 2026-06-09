try {
    $r = Invoke-WebRequest 'http://localhost:8080/healthcare/home' -UseBasicParsing
    Write-Host "HTTP Status: $($r.StatusCode)"
    Write-Host $r.Content.Substring(0, [Math]::Min(1500, $r.Content.Length))
} catch {
    $errResp = $_.Exception.Response
    if ($errResp) {
        $reader = New-Object System.IO.StreamReader($errResp.GetResponseStream())
        $body = $reader.ReadToEnd()
        Write-Host "Error Body: $($body.Substring(0, [Math]::Min(2000, $body.Length)))"
    } else {
        Write-Host "Error: $($_.Exception.Message)"
    }
}
