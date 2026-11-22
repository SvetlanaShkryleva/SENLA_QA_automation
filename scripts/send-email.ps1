param(
    [string]$RunNumber,
    [string]$Status,
    [string]$RunId
)

$body = @"
<h2>Результаты автотестов</h2>
<p><b>Проект:</b> $env:GITHUB_REPOSITORY</p>
<p><b>Номер сборки:</b> $RunNumber</p>
<p><b>Статус:</b> $Status</p>
<p><a href="https://github.com/$env:GITHUB_REPOSITORY/actions/runs/$RunId">Открыть запуск в GitHub Actions</a></p>
<p>Полный HTML-отчёт Allure во вложении.</p>
"@

$mail = New-Object System.Net.Mail.MailMessage
$mail.From = New-Object System.Net.Mail.MailAddress($env:SMTP_USERNAME)
$mail.To.Add($env:EMAIL_TO)
$mail.Subject = "Отчет о тестах: сборка #$RunNumber - $Status"
$mail.SubjectEncoding = [System.Text.Encoding]::UTF8
$mail.Body = $body
$mail.BodyEncoding = [System.Text.Encoding]::UTF8
$mail.IsBodyHtml = $true

if (Test-Path "allure-report.zip") {
    $attachment = New-Object System.Net.Mail.Attachment("allure-report.zip")
    $mail.Attachments.Add($attachment)
}

$smtp = New-Object System.Net.Mail.SmtpClient($env:SMTP_SERVER, [int]$env:SMTP_PORT)
$smtp.EnableSsl = $true
$smtp.Credentials = New-Object System.Management.Automation.PSCredential(
    $env:SMTP_USERNAME,
    (ConvertTo-SecureString $env:SMTP_PASSWORD -AsPlainText -Force)
)
$smtp.Send($mail)